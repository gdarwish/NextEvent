package com.nextevent.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nextevent.DatabaseHandler;
import com.nextevent.JavaBeans.CustomRecyclerviewAdapter;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import java.util.ArrayList;

import static com.nextevent.MainActivity.fab;

/**
 * @author Ghaith Darwish
 * @last Modified: 08/04/2020
 * @since 04/01/2020
 */
public class SavedFragment extends Fragment {

    private RecyclerView recyclerView;
    private CustomRecyclerviewAdapter adapter;
    private ArrayList<Event> events;
    private TextView savedHint;

    public SavedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        fab.hide();

        // Create SharedPreferences for storing and changing the layout
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int layout = sharedPreferences.getInt("LAYOUT", R.layout.event_item_model);

        savedHint = view.findViewById(R.id.savedHint);
        // link recycler view with xml
        recyclerView = view.findViewById(R.id.recyclerView);
        // create data database object
        DatabaseHandler db = new DatabaseHandler(getContext());

        // get all events from the database and store in events ArrayList
        events = db.getSavedEvents();

        // check if there's any save events
        if (events.size() > 0) {
            // hide the hint text
            savedHint.setVisibility(View.GONE);
        }
        // create CustomRecyclerviewAdapter object and give it the events lists, and savedEventToDetails id
        adapter = new CustomRecyclerviewAdapter(events, getContext(), R.id.action_savedFragment_to_detailViewPagerFragment, true);

        // setting the LayoutManager according to which layout is displayed
        if (layout == R.layout.recycler_grid_layout)
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // set the recyclerview adapter
        recyclerView.setAdapter(adapter);
        return view;
    }
}
