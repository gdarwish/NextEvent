package com.nextevent.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.nextevent.API.APIResponse;
import com.nextevent.API.EventSingleton;
import com.nextevent.JavaBeans.CustomRecyclerviewAdapter;
import com.nextevent.JavaBeans.Event;
import com.nextevent.JavaBeans.EventResult;
import com.nextevent.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;

import static com.nextevent.MainActivity.fab;


/**
 * @author Ghaith Darwish
 * @author Abel Anderson
 * @since 08/04/2020
 */
public class EventsFragment extends Fragment implements APIResponse {

    RecyclerView recyclerView;
    CustomRecyclerviewAdapter adapter;
    ArrayList<Event> events = new ArrayList<>();
    EditText searBar;
    TextView noEventsText;

    HashMap<String, String> headers = new HashMap<>();
    String url = "https://api.predicthq.com/v1/events/?q=";

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_events, container, false);

        // Create SharedPreferences for storing and changing the layout
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int layout = sharedPreferences.getInt("LAYOUT", R.layout.event_item_model);

        fab.hide();
        searBar = view.findViewById(R.id.searchBar);
        recyclerView = view.findViewById(R.id.eventRecyclerView);

        // setting the LayoutManager according to which layout is displayed
        if (layout == R.layout.recycler_grid_layout)
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        noEventsText = view.findViewById(R.id.noEventsText);
        // API KEY
        headers.put("Authorization", getString(R.string.api_key));

        // creating new instance of CustomRecyclerviewAdapter
        adapter = new CustomRecyclerviewAdapter(events, getActivity(), R.id.eventToDetail, false);
        // Setting the adapter for the recyclerview
        recyclerView.setAdapter(adapter);

        searBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (textView.getText().toString().isEmpty()) return false;
                String urlParams = textView.getText().toString().trim();

                //Grab the Shared Preferences
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

                //Check if they are searching for future events
                if(sharedPreferences.getBoolean(SettingFragment.FUTURE_EVENTS, false)) {
                    String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    urlParams += "&active.gte=" + currentDate;
                }

                //Check if they are searching for local events
                if(sharedPreferences.getBoolean(SettingFragment.LOCAL_EVENTS, false)) {
                    urlParams += "&country=CA";
                }

                //Search the API
                search(url + urlParams);

                noEventsText.setVisibility(view.GONE);
                return true;
            }
        });
        return view;
    }

    /**
     * Get data from API when searching
     *
     * @param search
     */
    private void search(String search) {
        EventSingleton.getInstance(getContext())
                .setHeaders(headers)
                .jsonObjectRequest(Request.Method.GET, search, null, this);
    }

    @Override
    public void onSuccess(Object result, int status) {
        Gson gson = new Gson();
        EventResult eventResult = gson.fromJson(result.toString(), EventResult.class);
        events.clear();

        for (Event event : eventResult.getResults()) {
            events.add(event);
        }
        adapter.updateList(events);
    }

    @Override
    public void onFailure(Object result, int status) {

    }
}
