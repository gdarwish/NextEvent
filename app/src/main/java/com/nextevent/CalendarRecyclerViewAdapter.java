package com.nextevent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kizitonwose.calendarview.CalendarView;
import com.nextevent.JavaBeans.CalendarMonth;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.temporal.WeekFields;

import java.util.ArrayList;
import java.util.Locale;

public class CalendarRecyclerViewAdapter extends RecyclerView.Adapter<CalendarRecyclerViewAdapter.CalendarViewHolder> {

    private ArrayList<CalendarMonth> calendarMonths;

    public CalendarRecyclerViewAdapter(ArrayList<CalendarMonth> calendarMonths) {
        this.calendarMonths = calendarMonths;
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

        holder.getCalendarView().setDayBinder(new CustomDayBinder());
        holder.getCalendarView().setup(calendarMonths.get(position).getMonth(), calendarMonths.get(position).getMonth(), firstDayOfWeek);
    }

    @Override
    public int getItemCount() {
        return calendarMonths.size();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder {

        private CalendarView calendarView;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            calendarView = itemView.findViewById(R.id.calendarView);
        }

        public CalendarView getCalendarView() {
            return calendarView;
        }
    }
}
