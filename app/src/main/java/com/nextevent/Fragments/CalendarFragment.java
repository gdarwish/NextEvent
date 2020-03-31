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

import com.nextevent.Calendar.CalendarRecyclerViewAdapter;
import com.nextevent.DatabaseHandler;
import com.nextevent.JavaBeans.CalendarMonth;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import org.threeten.bp.YearMonth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

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

        //Old CalendarView Code - Keeping for reference

//        CalendarView calendarView = view.findViewById(R.id.calendarView);
//        calendarView.setDayBinder(new CustomDayBinder());
//
//        YearMonth currentMonth = YearMonth.now();
//        YearMonth firstMonth = currentMonth.minusMonths(10);
//        YearMonth lastMonth = currentMonth.plusMonths(10);
//        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
//
//        calendarView.setup(firstMonth, lastMonth, firstDayOfWeek);
//        calendarView.setHasBoundaries(true);
//        calendarView.setScrollMode(ScrollMode.PAGED);
//        calendarView.setOrientation(RecyclerView.HORIZONTAL);
//        calendarView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
//        calendarView.setMaxRowCount(6);
//        calendarView.scrollToMonth(currentMonth);


        //Get all events from the Database
        DatabaseHandler db = new DatabaseHandler(getContext());
        ArrayList<Event> events = db.getAllEvents();

        // Date Format of Api - 2020-04-10T00:00:00Z

        //Create new ArrayList for Adapter
        ArrayList<CalendarMonth> calendarMonths = new ArrayList<>();

        //Get the current Month
        YearMonth currentMonth = YearMonth.now();

        //Populate the Adapter ArrayList
        for (int i = -12; i < 12; i++) {
            HashMap<Event, Date> currentEvents = filterEvents(events, currentMonth.plusMonths(i));
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

    private HashMap<Event, Date> filterEvents(ArrayList<Event> events, YearMonth filterDate) {

        HashMap<Event, Date> filterdArrayList = new HashMap<>();

        //Create the DateFormatter
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        //Loop through the events and grab the events from the current month
        for (Event event : events) {
            try {
                Date eventDate = formatter.parse(event.getStart());
                if (eventDate.getMonth() == filterDate.getMonth().getValue() && eventDate.getYear() == filterDate.getYear()) {
                    filterdArrayList.put(event, eventDate);
                }
            } catch (ParseException ignored) {
            }
        }

        try {
            filterdArrayList.put(new Event("", "WORK", "", "", new String[]{}, 0, 0, "", "", new double[]{}, "", ""), formatter.parse(filterDate.getYear() + "-" + filterDate.getMonth().getValue() + "-23"));
        } catch(ParseException ignored) { }

        return filterdArrayList;
    }
}