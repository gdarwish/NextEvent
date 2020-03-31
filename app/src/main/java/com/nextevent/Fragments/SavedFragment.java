package com.nextevent.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextevent.DatabaseHandler;
import com.nextevent.JavaBeans.CustomRecyclerviewAdapter;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedFragment extends Fragment {

    RecyclerView recyclerView;
    CustomRecyclerviewAdapter adapter;
    ArrayList<Event> events;

    public SavedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        DatabaseHandler db = new DatabaseHandler(getContext());

//        Log.e("SavedEvents", "Working"+db.getAllEvents());
//
//        if (!db.getAllEvents().isEmpty()){
//            events = db.getAllEvents();
//            Log.e("SavedEvents", "Working"+db.getAllEvents());
//        }else {
//            Log.e("SavedEvents", "not working"+db.getAllEvents());
//        }

            events = db.getAllEvents();
            adapter = new CustomRecyclerviewAdapter(events, getContext(), R.id.savedEventToDetails);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);

        return view;
    }

}
