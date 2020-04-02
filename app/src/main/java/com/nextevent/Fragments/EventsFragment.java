package com.nextevent.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ca.alidali.mainapi.APIResponse;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.nextevent.API.EventSingleton;
import com.nextevent.JavaBeans.CustomRecyclerviewAdapter;
import com.nextevent.JavaBeans.Event;
import com.nextevent.JavaBeans.EventResult;
import com.nextevent.R;

import java.util.ArrayList;
import java.util.HashMap;

import static com.nextevent.MainActivity.fab;


/**
 * @author Ghaith Darwish
 * @Last Modified: 30/03/2020
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

        fab.hide();
        searBar = view.findViewById(R.id.searchBar);
        recyclerView = view.findViewById(R.id.eventRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        noEventsText = view.findViewById(R.id.noEventsText);
        // API KEY
        //headers.put("Authorization", getString(R.string.api_key));

        // creating new instance of CustomRecyclerviewAdapter
        adapter = new CustomRecyclerviewAdapter(events, getActivity(), R.id.eventToDetail, false);
        // Setting the adapter for the recyclerview
        recyclerView.setAdapter(adapter);

        searBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (textView.getText().toString().isEmpty()) return false;
                search(url + textView.getText().toString());
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
        search += "&country=CA";
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
