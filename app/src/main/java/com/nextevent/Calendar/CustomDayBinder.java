package com.nextevent.Calendar;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Binds a day to a DayViewContainer. This class is responsible for setting the date and events on the DayViewContainer for that day.
 *
 * @author Abel Anderson
 * @version 1.1
 * @since 02/04/2020
 */

public class CustomDayBinder implements DayBinder<DayViewContainer> {

    private HashMap<LocalDate, ArrayList<Event>> events;
    private RecyclerView recyclerView;

    CustomDayBinder(HashMap<LocalDate, ArrayList<Event>> events, RecyclerView recyclerView) {
        this.events = events;
        this.recyclerView = recyclerView;
    }

    /**
     * Sets the values of the dayViewContainer for the current day. Also check for events on the current day.
     *
     * @param dayViewContainer The DayViewContainer that is being set.
     * @param day              The Day that the DayViewContainer is being set up for.
     */
    @Override
    public void bind(DayViewContainer dayViewContainer, CalendarDay day) {
        dayViewContainer.dayText.setText(day.getDay() + "");

        if (events.containsKey(day.getDate())) {
            for (Event event : events.get(day.getDate())) {
                dayViewContainer.eventDisplay.addView(View.inflate(dayViewContainer.getView().getContext(), R.layout.calendar_event_circle, null));
            }

            dayViewContainer.events = events.get(day.getDate());
        }
    }

    @Override
    public DayViewContainer create(View view) {
        return new DayViewContainer(view, recyclerView);
    }
}