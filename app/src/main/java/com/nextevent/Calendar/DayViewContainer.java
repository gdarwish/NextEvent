package com.nextevent.Calendar;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kizitonwose.calendarview.ui.ViewContainer;
import com.nextevent.R;

/**
 * The DayViewContainer for the Calendar
 *
 * @author Abel Anderson
 * @since 02/04/2020
 * @version 1.2
 */

public class DayViewContainer extends ViewContainer {

    TextView dayText;
    LinearLayout eventDisplay;

    public DayViewContainer(View view) {
        super(view);

        dayText = view.findViewById(R.id.calendarDayText);
        eventDisplay = view.findViewById(R.id.eventDisplay);
    }
}