package com.nextevent.Calendar;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
    private DayViewContainer lastSelectedDay;

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
        final DayViewContainer viewContainer = dayViewContainer;

        viewContainer.getDayText().setText(day.getDay() + "");

        if (events.containsKey(day.getDate())) {
            for (Event ignored : events.get(day.getDate())) {
                viewContainer.getEventDisplay().addView(View.inflate(viewContainer.getView().getContext(), R.layout.calendar_event_circle, null));
            }

            viewContainer.setEvents(events.get(day.getDate()));
        }

        if(day.getDate().equals(LocalDate.now())) {
            viewContainer.getDayView().setBackgroundResource(R.drawable.circle);
        } else {
            viewContainer.getDayView().setBackgroundResource(R.drawable.day_unselected);
        }

        viewContainer.getDayView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                view.setBackgroundResource(R.drawable.day_selected);
                viewContainer.getDayText().setTextColor(Color.WHITE);

                if(lastSelectedDay != null) {
                    lastSelectedDay.getView().setBackgroundResource(R.drawable.day_unselected);
                    lastSelectedDay.getDayText().setTextColor(Color.parseColor("#808080"));
                }

                lastSelectedDay = viewContainer;

                CalendarEventRecyclerViewAdapter recyclerViewAdapter = (CalendarEventRecyclerViewAdapter) recyclerView.getAdapter();
                recyclerViewAdapter.events = viewContainer.getEvents();
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public DayViewContainer create(View view) {
        return new DayViewContainer(view, recyclerView);
    }
}