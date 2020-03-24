package com.nextevent.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nextevent.JavaBeans.Event;
import com.nextevent.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailEventFragment extends Fragment {

    Event event;

    public DetailEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_event, container, false);


        TextView info = view.findViewById(R.id.info);

        if (getArguments() != null){
            event = getArguments().getParcelable("event");
            info.setText(event.getDescription());
        }


        return view;
    }
}
