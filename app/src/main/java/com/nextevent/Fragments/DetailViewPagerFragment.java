package com.nextevent.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextevent.DatabaseHandler;
import com.nextevent.JavaBeans.ViewPagerDetailAdapter;
import com.nextevent.JavaBeans.Event;
import com.nextevent.JavaBeans.ZoomOutPageTransformer;
import com.nextevent.R;

import java.util.ArrayList;

/**
 * @author Ghaith Darwish
 * @Last Modified: 04/07/2020
 * @since 04/07/2020
 */
public class DetailViewPagerFragment extends Fragment {

    private ArrayList<Event> events;
    private ViewPager viewPager;
    private ViewPagerDetailAdapter adapter;
    DatabaseHandler db;
    Event event;

    public DetailViewPagerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getArguments if not null
        if (getArguments() != null)
            event = getArguments().getParcelable("event");
        // instantiate new database object
        db = new DatabaseHandler(getContext());
        // store all savedEvents in events
        events = db.getSavedEvents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_view_pager, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        // Set ViewPager animation
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        adapter = new ViewPagerDetailAdapter(getChildFragmentManager(), events);
        adapter.getItem(adapter.getItemPosition(event));
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(adapter.getItemPosition(event));
        return view;
    }
}
