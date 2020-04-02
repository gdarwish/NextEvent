package com.nextevent.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextevent.DatabaseHandler;
import com.nextevent.JavaBeans.CustomRecyclerviewAdapter;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import java.util.ArrayList;

/**
 * @author Ghaith Darwish
 * @last modified: 30/3/2020
 */
public class SavedFragment extends Fragment {

    RecyclerView recyclerView;
    CustomRecyclerviewAdapter adapter;
    ArrayList<Event> events;

    public SavedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        // link recycler view with xml
        recyclerView = view.findViewById(R.id.recyclerView);
        // create data database object
        DatabaseHandler db = new DatabaseHandler(getContext());

        // get all events from the database and store in events ArrayList
        events = db.getAllEvents();
        // create CustomRecyclerviewAdapter object and give it the events lists, and savedEventToDetails id
        adapter = new CustomRecyclerviewAdapter(events, getContext(), R.id.savedEventToDetails, true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // set the recyclerview adapter
        recyclerView.setAdapter(adapter);
        return view;
    }
}
