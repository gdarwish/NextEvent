package com.nextevent.Calendar;

import android.view.View;

import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.nextevent.JavaBeans.Event;

import org.threeten.bp.LocalDate;

import java.util.HashMap;
import java.util.Map;

public class CustomDayBinder implements DayBinder<DayViewContainer> {

    private HashMap<Event, LocalDate> events;

    CustomDayBinder(HashMap<Event, LocalDate> events) {
        this.events = events;
    }

    @Override
    public void bind(DayViewContainer dayViewContainer, CalendarDay day) {
        dayViewContainer.dayText.setText(day.getDay());

        for (Map.Entry<Event, LocalDate> event : events.entrySet()) {
            if (event.getValue().getDayOfMonth() == day.getDay()) {
                dayViewContainer.eventText.setText(event.getKey().getTitle());
                dayViewContainer.eventText.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public DayViewContainer create(View view) {
        return new DayViewContainer(view);
    }
}