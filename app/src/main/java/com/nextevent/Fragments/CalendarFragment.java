package com.nextevent.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextevent.Calendar.CalendarEventRecyclerViewAdapter;
import com.nextevent.Calendar.CalendarRecyclerViewAdapter;
import com.nextevent.DatabaseHandler;
import com.nextevent.JavaBeans.CalendarMonth;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        //Get all events from the Database
        DatabaseHandler db = new DatabaseHandler(getContext());
        ArrayList<Event> events = db.getAddedEvents();

        //Create new ArrayList for Adapter
        ArrayList<CalendarMonth> calendarMonths = new ArrayList<>();

        //Get the current Month
        YearMonth currentMonth = YearMonth.now();

        //Populate the Adapter ArrayList
        for (int i = -12; i < 12; i++) {
            HashMap<LocalDate, ArrayList<Event>> currentEvents = filterEvents(events, currentMonth.plusMonths(i));
            calendarMonths.add(new CalendarMonth(currentMonth.plusMonths(i), currentEvents));
        }

        //Set up the Calendar Events RecyclerView
        RecyclerView calendarEventRecyclerView = view.findViewById(R.id.calendarEventsRecyclerView);
        calendarEventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        calendarEventRecyclerView.setAdapter(new CalendarEventRecyclerViewAdapter(new ArrayList<Event>()));

        //Set up the Calendar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.calendarRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(new CalendarRecyclerViewAdapter(calendarMonths, calendarEventRecyclerView));

        //Make sure to scroll to the current month
        recyclerView.scrollToPosition(12);

        //Add a SnapHelper to the RecyclerView
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    private HashMap<LocalDate, ArrayList<Event>> filterEvents(ArrayList<Event> events, YearMonth filterDate) {

        //Create the HashMap
        HashMap<LocalDate, ArrayList<Event>> filterdArrayList = new HashMap<>();

        //Create the Date Formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //Loop through the events and grab the events from the current month
        for (Event event : events) {

            //Parse the LocalDate from the event
            LocalDate eventDate = LocalDate.parse(event.getParsableStartDate(), formatter);

            //Check if the event matches the Month & Year passed in
            if (eventDate.getMonth().getValue() == filterDate.getMonth().getValue() && eventDate.getYear() == filterDate.getYear()) {

                //Create a new reference to this date inside the HashMap if it does not already exist
                if(filterdArrayList.containsKey(eventDate)) {
                    filterdArrayList.get(eventDate).add(event);
                } else {
                    filterdArrayList.put(eventDate, new ArrayList<>(Collections.singletonList(event)));
                }
            }
        }

        return filterdArrayList;
    }
}