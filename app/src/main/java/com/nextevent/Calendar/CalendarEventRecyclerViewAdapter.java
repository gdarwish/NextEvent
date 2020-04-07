package com.nextevent.Calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import java.util.ArrayList;

/**
 * @author Abel Anderson
 * @since 04/04/2020
 * @version 1.0
 */

public class CalendarEventRecyclerViewAdapter extends RecyclerView.Adapter<CalendarEventRecyclerViewAdapter.CalendarEventViewHolder> {
    ArrayList<Event> events;

    public CalendarEventRecyclerViewAdapter(ArrayList<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public CalendarEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_event, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new CalendarEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarEventViewHolder holder, int position) {
        holder.getTitleTextView().setText(events.get(position).getTitle());
        holder.getDateTextView().setText(events.get(position).getFormattedStartDate());
        holder.getCountryTextView().setText(events.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class CalendarEventViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dateTextView;
        private TextView countryTextView;

        CalendarEventViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            countryTextView = itemView.findViewById(R.id.countryTextView);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getDateTextView() {
            return dateTextView;
        }

        public TextView getCountryTextView() {
            return countryTextView;
        }
    }
}
