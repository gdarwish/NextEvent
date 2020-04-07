package com.nextevent.Calendar;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kizitonwose.calendarview.ui.ViewContainer;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import java.util.ArrayList;

/**
 * The DayViewContainer for the Calendar
 *
 * @author Abel Anderson
 * @since 06/04/2020
 * @version 1.4
 */

class DayViewContainer extends ViewContainer{

    private View dayView;
    private TextView dayText;
    private LinearLayout eventDisplay;
    private ArrayList<Event> events;

    DayViewContainer(View dayView, final RecyclerView recyclerView) {
        super(dayView);

        this.dayView = dayView;
        this.events = new ArrayList<>();
        this.dayText = dayView.findViewById(R.id.calendarDayText);
        this.eventDisplay = dayView.findViewById(R.id.eventDisplay);
    }

    public View getDayView() {
        return dayView;
    }

    public TextView getDayText() {
        return dayText;
    }

    public LinearLayout getEventDisplay() {
        return eventDisplay;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}