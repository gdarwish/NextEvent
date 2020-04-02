package com.nextevent.Calendar;

import android.view.View;

import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import org.threeten.bp.LocalDate;

import java.util.HashMap;
import java.util.Map;

/**
 * Binds a day to a DayViewContainer. This class is responsible for setting the date and events on the DayViewContainer for that day.
 *
 * @author Abel Anderson
 * @since 02/04/2020
 * @version 1.1
 */

public class CustomDayBinder implements DayBinder<DayViewContainer> {

    private HashMap<Event, LocalDate> events;

    CustomDayBinder(HashMap<Event, LocalDate> events) {
        this.events = events;
    }

    /**
     * Sets the values of the dayViewContainer for the current day. Also check for events on the current day.
     * @param dayViewContainer The DayViewContainer that is being set.
     * @param day The Day that the DayViewContainer is being set up for.
     */
    @Override
    public void bind(DayViewContainer dayViewContainer, CalendarDay day) {
        dayViewContainer.dayText.setText(day.getDay() + "");

        for (Map.Entry<Event, LocalDate> event : events.entrySet()) {
            if (event.getValue().getDayOfMonth() == day.getDay()) {
                dayViewContainer.eventDisplay.addView(View.inflate(dayViewContainer.getView().getContext(), R.layout.calendar_event_circle, null));
            }
        }
    }

    @Override
    public DayViewContainer create(View view) {
        return new DayViewContainer(view);
    }
}