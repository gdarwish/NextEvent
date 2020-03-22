package com.nextevent.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nextevent.JavaBeans.Event;
import com.nextevent.R;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailEventFragment extends Fragment {

    Event event;
    TextView date;
    TextView location;
    TextView title;
    TextView description;
    ImageView eventImage;

    ImageButton saveButton;
    ImageButton webButton;
    ImageButton directionButton;
    ImageButton shareButton;
    Button addEventButton;
    LinearLayout labels;

    public DetailEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_event, container, false);

        title = view.findViewById(R.id.title);
        date = view.findViewById(R.id.dateText);
        location = view.findViewById(R.id.locationText);
        description = view.findViewById(R.id.descriptionText);
        saveButton = view.findViewById(R.id.saveButton);
        webButton = view.findViewById(R.id.webButton);
        directionButton = view.findViewById(R.id.directionsButton);
        shareButton = view.findViewById(R.id.shareButton);
        addEventButton = view.findViewById(R.id.addButton);
        labels = view.findViewById(R.id.labels);
        eventImage = view.findViewById(R.id.eventImage);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add the event to database as saved event
            }
        });

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open a web page about the event with more  info
            }
        });

        directionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get direction to the event location by google maps
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // share info about the event by a text
            }
        });

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // should add the event to schedule with some info (title, date....)
            }
        });


        if (getArguments() != null) {
            event = getArguments().getParcelable("event");
            title.setText(event.getTitle());
            date.setText(event.getStart());
            location.setText(event.getCountry());
            description.setText(event.getDescription());
            Picasso.get().load(event.getImage()).placeholder(R.drawable.placeholder).into(eventImage);

            for (String label : event.getLabels()) {
                TextView textView = new TextView(getContext());
                textView.setText(label);
                textView.setPadding(5, 0, 5, 5);
                textView.setBackgroundResource(R.drawable.border);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 10, 0);
                textView.setLayoutParams(params);
                labels.addView(textView);
            }
        }
        return view;
    }
}
