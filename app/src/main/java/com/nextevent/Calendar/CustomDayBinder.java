package com.nextevent.Calendar;


import android.util.Log;
import android.view.View;

import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.nextevent.JavaBeans.Event;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomDayBinder implements DayBinder<DayViewContainer> {

    DayViewContainer viewContainer;
    HashMap<Event, Date> events;

    CustomDayBinder(HashMap<Event, Date> events) {
        this.events = events;
    }

    @Override
    public void bind(DayViewContainer dayViewContainer, CalendarDay day) {
        dayViewContainer.dayText.setText(day.getDay() + "");

       for(Map.Entry<Event, Date> event : events.entrySet()) {
           Log.d("DATE TEST", event.getValue().getDate() + "");
           Log.d("DATE TEST", day.getDay() + "");
            if(event.getValue().getDay() == day.getDay()) {
                dayViewContainer.eventText.setText(event.getKey().getTitle());
                dayViewContainer.eventText.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public DayViewContainer create(View view) {
        viewContainer = new DayViewContainer(view);
        return viewContainer;
    }
}
