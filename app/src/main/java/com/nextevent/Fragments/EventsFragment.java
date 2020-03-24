package com.nextevent.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ca.alidali.mainapi.APIResponse;
import ca.alidali.mainapi.MainAPI;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.nextevent.JavaBeans.CustomRecyclerviewAdapter;
import com.nextevent.JavaBeans.Event;
import com.nextevent.JavaBeans.EventModel;
import com.nextevent.JavaBeans.EventResult;
import com.nextevent.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.nextevent.MainActivity.fab;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment  implements APIResponse {

    RecyclerView recyclerView;
    CustomRecyclerviewAdapter adapter;
    ArrayList<Event> events = new ArrayList<>();
    EditText searBar;


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

        HashMap<String, String> headers = new HashMap<>();
        // API KEY
        //headers.put("");
        String url = "https://api.predicthq.com/v1/events?within=1km@42.2951067,-83.072922";

//        MainAPI.getInstance(getContext())
//                .setHeaders(headers)
//                .stringRequest(MainAPI.Method.GET, url, null, this);

//        events.add(new Event("Ghaith", "Ghaith", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUVJQyaDhAU2TrCTE86fMkrchtrGkGd8zmO_mFMWcZ83fGs7Oh"));
//        events.add(new Event("Ghaith", "Ghaith", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUVJQyaDhAU2TrCTE86fMkrchtrGkGd8zmO_mFMWcZ83fGs7Oh"));
//        events.add(new Event("Ghaith", "Ghaith", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUVJQyaDhAU2TrCTE86fMkrchtrGkGd8zmO_mFMWcZ83fGs7Oh"));
//        events.add(new Event("Ghaith", "Ghaith", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUVJQyaDhAU2TrCTE86fMkrchtrGkGd8zmO_mFMWcZ83fGs7Oh"));
//        events.add(new Event("Ghaith", "Ghaith", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUVJQyaDhAU2TrCTE86fMkrchtrGkGd8zmO_mFMWcZ83fGs7Oh"));
//        events.add(new Event("Ghaith", "Ghaith", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUVJQyaDhAU2TrCTE86fMkrchtrGkGd8zmO_mFMWcZ83fGs7Oh"));
//        events.add(new Event("Ghaith", "Ghaith", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUVJQyaDhAU2TrCTE86fMkrchtrGkGd8zmO_mFMWcZ83fGs7Oh"));

        // creating new instance of CustomRecyclerviewAdapter

        adapter = new CustomRecyclerviewAdapter(events, getActivity());
        // Setting the adapter for the recyclerview
        recyclerView.setAdapter(adapter);

        searBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // we will use this method to get the result of the search when we implement the API

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        return view;
    }

    @Override
    public void onSuccess(Object result, int status) {
//        Log.e("RESULT", "String\n" + result.toString());
        Gson gson = new Gson();
        EventResult eventResult = gson.fromJson(result.toString(), EventResult.class);
//        Log.e("RESULT", "JSON\n" + eventResult.toString());

        events.clear();

        for (Event event:
             eventResult.getResults()) {
            events.add(event);
        }
        adapter.updateList(events);
    }

    @Override
    public void onFailure(Object result, int status) {

    }
}
