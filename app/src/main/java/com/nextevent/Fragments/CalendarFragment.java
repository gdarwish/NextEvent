package com.nextevent.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextevent.Calendar.CalendarRecyclerViewAdapter;
import com.nextevent.DatabaseHandler;
import com.nextevent.JavaBeans.CalendarMonth;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
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
        ArrayList<Event> events = db.getAllEvents();

        //Create new ArrayList for Adapter
        ArrayList<CalendarMonth> calendarMonths = new ArrayList<>();

        //Get the current Month
        YearMonth currentMonth = YearMonth.now();

        //Populate the Adapter ArrayList
        for (int i = -12; i < 12; i++) {
            HashMap<Event, LocalDate> currentEvents = filterEvents(events, currentMonth.plusMonths(i));
            calendarMonths.add(new CalendarMonth(currentMonth.plusMonths(i), currentEvents));
        }

        //Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.calendarRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(new CalendarRecyclerViewAdapter(calendarMonths));

        //Make sure to scroll to the current month
        recyclerView.scrollToPosition(12);

        //Add a SnapHelper to the RecyclerView
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    private HashMap<Event, LocalDate> filterEvents(ArrayList<Event> events, YearMonth filterDate) {

        HashMap<Event, LocalDate> filterdArrayList = new HashMap<>();

        //Create the Date Formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //Loop through the events and grab the events from the current month
        for (Event event : events) {
            LocalDate eventDate = LocalDate.parse(event.getParsableStartDate(), formatter);
            if (eventDate.getMonth().getValue() == filterDate.getMonth().getValue() && eventDate.getYear() == filterDate.getYear()) {
                filterdArrayList.put(event, eventDate);
            }
        }

        return filterdArrayList;
    }
}