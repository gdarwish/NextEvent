package com.nextevent.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
 * @author Ghaith Darwish
 * @author Abel Anderson
 * @since 06/04/2020
 */
public class DetailEventFragment extends Fragment {

    private Event event;
    private TextView date;
    private TextView location;
    private TextView title;
    private TextView description;
    private ImageView eventImage;

    private ImageButton addButton;
    private ImageButton webButton;
    private ImageButton directionButton;
    private ImageButton shareButton;
    private Button addEventButton;
    private LinearLayout labels;
    private DatabaseHandler db;

    public DetailEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            event = getArguments().getParcelable("event");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_event, container, false);

        // link with xml
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
        db = new DatabaseHandler(getContext());

        // Setting the text from the event that was received from the bundle
        title.setText(event.getTitle());
        date.setText(event.getStart());
        location.setText(event.getCountry());
        description.setText(event.getDescription());
        Picasso.get().load(event.getImage()).placeholder(R.drawable.placeholder).into(eventImage);

        // Setting the label text from the event that was received from the bundle
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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add event to the database if it's not added already
                Event dbEvent = db.getEvent(event.getId());
                event.setIsSaved(true);

                if(dbEvent == null) {
                    db.addEvent(event);
                    Toast.makeText(getContext(), "Event saved", Toast.LENGTH_SHORT).show();
                } else if(dbEvent.getIsSaved() != 1) {
                    db.updateEvent(event);
                    Toast.makeText(getContext(), "Event saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Event already saved", Toast.LENGTH_SHORT).show();
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
                Uri locationUri = Uri.parse("geo:0,0?q=" + event.getLat() + "," + event.getLang());
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
                Event dbEvent = db.getEvent(event.getId());
                event.setIsAdded(true);

                if(dbEvent == null) {
                    db.addEvent(event);
                    Toast.makeText(getContext(), "Event added", Toast.LENGTH_SHORT).show();
                } else if(dbEvent.getIsAdded() != 1) {
                    db.updateEvent(event);
                    Toast.makeText(getContext(), "Event added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Event already added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}