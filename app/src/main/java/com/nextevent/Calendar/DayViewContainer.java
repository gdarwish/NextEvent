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
 * @since 02/04/2020
 * @version 1.3
 */

public class DayViewContainer extends ViewContainer{

    TextView dayText;
    LinearLayout eventDisplay;
    ArrayList<Event> events;
    private RecyclerView recyclerView;

    DayViewContainer(View view, final RecyclerView recyclerView) {
        super(view);

        dayText = view.findViewById(R.id.calendarDayText);
        eventDisplay = view.findViewById(R.id.eventDisplay);
        this.recyclerView = recyclerView;
        this.events = new ArrayList<>();

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CalendarEventRecyclerViewAdapter recyclerViewAdapter = (CalendarEventRecyclerViewAdapter) recyclerView.getAdapter();
                recyclerViewAdapter.events = events;
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }
}