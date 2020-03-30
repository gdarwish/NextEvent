package com.nextevent;


import android.view.View;

import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.ui.DayBinder;

public class CustomDayBinder implements DayBinder<DayViewContainer> {

    DayViewContainer viewContainer;

    @Override
    public void bind(DayViewContainer dayViewContainer, CalendarDay day) {
        dayViewContainer.textView.setText(day.getDay() + "");
    }

    @Override
    public DayViewContainer create(View view) {
        viewContainer = new DayViewContainer(view);
        return viewContainer;
    }
}
