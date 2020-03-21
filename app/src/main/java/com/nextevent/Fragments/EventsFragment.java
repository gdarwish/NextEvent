package com.nextevent.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ca.alidali.mainapi.APIResponse;
import ca.alidali.mainapi.MainAPI;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nextevent.JavaBeans.CustomRecyclerviewAdapter;
import com.nextevent.JavaBeans.Event;
import com.nextevent.JavaBeans.EventResult;
import com.nextevent.R;

import java.util.ArrayList;
import java.util.HashMap;

import static com.nextevent.MainActivity.fab;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment implements APIResponse {

    RecyclerView recyclerView;
    CustomRecyclerviewAdapter adapter;
    ArrayList<Event> events = new ArrayList<>();
    EditText searBar;

    HashMap<String, String> headers = new HashMap<>();
    String url = "https://api.predicthq.com/v1/events/?q=";


    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        fab.hide();
        searBar = view.findViewById(R.id.searchBar);
        recyclerView = view.findViewById(R.id.eventRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // API KEY
        headers.put("Authorization", getString(R.string.api_key));
        // API URL


//        events.add(new Event("Ghaith", "09/09/2020", "CA", "https://upload.wikimedia.org/wikipedia/commons/8/87/Google_Chrome_icon_%282011%29.png"));
//        events.add(new Event("Ghaith", "09/09/2020", "CA", "https://imgbin.com/i/fav/android-chrome-512x512.png"));
//        events.add(new Event("Ghaith", "09/09/2020", "CA", "https://icons.iconarchive.com/icons/martz90/circle/512/video-camera-icon.png"));
//        events.add(new Event("Ghaith", "09/09/2020", "CA", "https://icons.iconarchive.com/icons/dtafalonso/android-l/512/WhatsApp-icon.png"));
//        events.add(new Event("Ghaith", "09/09/2020", "CA", "https://www.lavalnews.ca/wp-content/uploads/2016/03/cropped-n-logo-black-outline-512-x-512.png"));
        // creating new instance of CustomRecyclerviewAdapter
        adapter = new CustomRecyclerviewAdapter(events, getActivity());
        // Setting the adapter for the recyclerview
        recyclerView.setAdapter(adapter);

        searBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (textView.getText().toString().isEmpty()) return false;
                search(url + textView.getText().toString());
                return true;
            }
        });

        return view;
    }

    private void search(String search) {
        search += "&country=CA";
        MainAPI.getInstance(getContext())
                .setHeaders(headers)
                .jsonObjectRequest(MainAPI.Method.GET, search, null, this);
    }

    @Override
    public void onSuccess(Object result, int status) {
//        Log.e("RESULT", "String\n" + result.toString());

        Gson gson = new Gson();
        EventResult eventResult = gson.fromJson(result.toString(), EventResult.class);
//        Log.e("RESULT", "JSON\n" + eventResult.toString());

        events.clear();

        for (Event event :
                eventResult.getResults()) {
            events.add(event);
        }
        adapter.updateList(events);
    }

    @Override
    public void onFailure(Object result, int status) {

    }
}
