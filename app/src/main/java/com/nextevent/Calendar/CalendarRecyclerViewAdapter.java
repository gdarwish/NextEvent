package com.nextevent.Calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kizitonwose.calendarview.CalendarView;
import com.nextevent.JavaBeans.CalendarMonth;
import com.nextevent.R;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.temporal.WeekFields;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Custom Adapter for the Calendar RecyclerView.
 *
 * @author Abel Anderson
 * @version 1.1
 * @since 30/03/2020
 */

public class CalendarRecyclerViewAdapter extends RecyclerView.Adapter<CalendarRecyclerViewAdapter.CalendarViewHolder> {

    private ArrayList<CalendarMonth> calendarMonths;
    private CalendarEventRecyclerViewAdapter eventRecyclerViewAdapter;
    private DayViewContainer lastSelectedDay;

    public CalendarRecyclerViewAdapter(ArrayList<CalendarMonth> calendarMonths, CalendarEventRecyclerViewAdapter eventRecyclerViewAdapter) {
        this.calendarMonths = calendarMonths;
        this.eventRecyclerViewAdapter = eventRecyclerViewAdapter;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_view, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();

        holder.getCalendarView().setDayBinder(new CustomDayBinder(calendarMonths.get(position).getEvents(), eventRecyclerViewAdapter, this));
        holder.getCalendarView().setup(calendarMonths.get(position).getMonth(), calendarMonths.get(position).getMonth(), firstDayOfWeek);
        String text = calendarMonths.get(position).getMonth().getMonth().toString() + " - " + calendarMonths.get(position).getMonth().getYear();
        holder.getMonthTextView().setText(text);
    }

    @Override
    public int getItemCount() {
        return calendarMonths.size();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder {

        private CalendarView calendarView;
        private TextView monthTextView;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            calendarView = itemView.findViewById(R.id.calendarView);
            monthTextView = itemView.findViewById(R.id.monthText);
        }

        public CalendarView getCalendarView() {
            return calendarView;
        }

        public TextView getMonthTextView() {
            return monthTextView;
        }
    }

    public DayViewContainer getLastSelectedDay() {
        return lastSelectedDay;
    }

    public void setLastSelectedDay(DayViewContainer lastSelectedDay) {
        this.lastSelectedDay = lastSelectedDay;
    }
}
