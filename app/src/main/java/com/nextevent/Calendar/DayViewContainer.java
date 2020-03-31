package com.nextevent.Calendar;

import android.view.View;
import android.widget.TextView;

import com.kizitonwose.calendarview.ui.ViewContainer;
import com.nextevent.R;

public class DayViewContainer extends ViewContainer {

    TextView dayText;
    TextView eventText;

    public DayViewContainer(View view) {
        super(view);

        dayText = view.findViewById(R.id.calendarDayText);
        eventText = view.findViewById(R.id.eventTag);
    }
}