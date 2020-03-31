package com.nextevent.Fragments;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import com.nextevent.DatabaseHandler;
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

    ImageButton addButton;
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
        addButton = view.findViewById(R.id.saveButton);
        webButton = view.findViewById(R.id.webButton);
        directionButton = view.findViewById(R.id.directionsButton);
        shareButton = view.findViewById(R.id.shareButton);
        addEventButton = view.findViewById(R.id.addButton);
        labels = view.findViewById(R.id.labels);
        eventImage = view.findViewById(R.id.eventImage);

        final DatabaseHandler db = new DatabaseHandler(getContext());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add the event to database as saved event

                if (getArguments() != null) {
                    event = getArguments().getParcelable("event");
                    db.addEvent(event);
                    Toast.makeText(getContext(),"Added successful",Toast.LENGTH_SHORT).show();

                }
            }
        });

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open a web page about the event with more  info
                Uri url = Uri.parse("https://www.google.com/search?q=" + title.getText());
                Intent i = new Intent(Intent.ACTION_VIEW, url);
                if (i.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(i);
                }
            }
        });

        directionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get direction to the event location by google maps
                Uri locationUri = Uri.parse("geo:0,0?q="+event.getLat()+","+event.getLang());
                Intent intent = new Intent(Intent.ACTION_VIEW, locationUri);
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // share info about the event by a text
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String body = "On:" + event.getStart() + "\n" + event.getDescription();
                String subject = "Hey! you need to check this out: " + event.getTitle();
                i.putExtra(Intent.EXTRA_SUBJECT, subject);
                i.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(i, "Share Using"));
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
