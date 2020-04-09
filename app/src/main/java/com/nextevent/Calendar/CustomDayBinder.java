package com.nextevent.Calendar;

import android.graphics.Color;
import android.view.View;

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
    private CalendarEventRecyclerViewAdapter eventRecyclerViewAdapter;
    private CalendarRecyclerViewAdapter parentRecyclerViewAdapter;

    CustomDayBinder(HashMap<LocalDate, ArrayList<Event>> events, CalendarEventRecyclerViewAdapter eventRecyclerViewAdapter, CalendarRecyclerViewAdapter parentRecyclerViewAdapter) {
        this.events = events;
        this.eventRecyclerViewAdapter = eventRecyclerViewAdapter;
        this.parentRecyclerViewAdapter = parentRecyclerViewAdapter;
    }

    /**
     * Sets the values of the dayViewContainer for the current day. Also check for events on the current day.
     *
     * @param dayViewContainer The DayViewContainer that is being set.
     * @param day              The Day that the DayViewContainer is being set up for.
     */
    @Override
    public void bind(DayViewContainer dayViewContainer, CalendarDay day) {
        final DayViewContainer viewContainer = dayViewContainer;

        //Set the Day & the day Text
        viewContainer.setDay(day);
        viewContainer.getDayText().setText(day.getDay() + "");

        //Check if there are any events on that day
        if (events.containsKey(day.getDate())) {
            for (Event ignored : events.get(day.getDate())) {
                viewContainer.getEventDisplay().addView(View.inflate(viewContainer.getView().getContext(), R.layout.calendar_event_circle, null));
            }

            viewContainer.setEvents(events.get(day.getDate()));
        }

        //Check if the day is the current date
        if (day.getDate().equals(LocalDate.now())) {
            viewContainer.getDayView().setBackgroundResource(R.drawable.circle);
        } else {
            viewContainer.getDayView().setBackgroundResource(R.drawable.day_unselected);
        }

        //Set an onClickListener for the DayView
        viewContainer.getDayView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Style the selected day
                view.setBackgroundResource(R.drawable.day_selected);
                viewContainer.getDayText().setTextColor(Color.WHITE);

                //Make sure the last day gets unselected
                if (parentRecyclerViewAdapter.getLastSelectedDay() != null) {
                    if (parentRecyclerViewAdapter.getLastSelectedDay().getDay().getDate().equals(LocalDate.now())) {
                        parentRecyclerViewAdapter.getLastSelectedDay().getDayView().setBackgroundResource(R.drawable.circle);
                    } else {
                        parentRecyclerViewAdapter.getLastSelectedDay().getDayView().setBackgroundResource(R.drawable.day_unselected);
                    }
                    parentRecyclerViewAdapter.getLastSelectedDay().getDayText().setTextColor(Color.parseColor("#808080"));
                }

                //Set the selectedDay to this date
                parentRecyclerViewAdapter.setLastSelectedDay(viewContainer);

                //Update the EventRecyclerViewAdapter with the current day's events
                eventRecyclerViewAdapter.setDayEventsView(viewContainer.getEventDisplay());
                eventRecyclerViewAdapter.setEvents(viewContainer.getEvents());
                eventRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public DayViewContainer create(View view) {
        return new DayViewContainer(view);
    }
}