package com.nextevent.Calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextevent.DatabaseHandler;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import java.util.ArrayList;

/**
 * @author Abel Anderson
 * @author Ghaith Darwish
 * @version 1.0
 * @since 07/04/2020
 */

public class CalendarEventRecyclerViewAdapter extends RecyclerView.Adapter<CalendarEventRecyclerViewAdapter.CalendarEventViewHolder> {
    private ArrayList<Event> events;
    private LinearLayout dayEventsView;
    private DatabaseHandler db;

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
        holder.getDateTextView().setText(events.get(position).getParsableStartDate());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class CalendarEventViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dateTextView;
        private ImageButton deleteButton;

        CalendarEventViewHolder(@NonNull View itemView) {
            super(itemView);

            db = new DatabaseHandler(itemView.getContext());

            titleTextView = itemView.findViewById(R.id.titleTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Creating AlertDialog when deleting an event
                    new AlertDialog.Builder(view.getContext()).setTitle("Delete")
                            .setMessage("Are you sure you want to delete " + events.get(getLayoutPosition()).getTitle() + " from you calendar?")
                            .setIcon(R.drawable.ic_warning_black_24dp)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Event event = events.get(getAdapterPosition());

                                    //Set event isAdded to false
                                    event.setIsAdded(false);

                                    //Check if we still need to keep the event
                                    if (event.getIsSaved() == 1) {
                                        db.updateEvent(event);
                                    } else {
                                        db.deleteEvent(event.getId());
                                    }

                                    //Remove the event from the list
                                    events.remove(event);

                                    //Remove the eventCircle from the Day
                                    dayEventsView.removeViewAt(0);
                                    notifyItemRemoved(getAdapterPosition());
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            });
        }

        TextView getTitleTextView() {
            return titleTextView;
        }

        TextView getDateTextView() {
            return dateTextView;
        }
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void setDayEventsView(LinearLayout dayEventsView) {
        this.dayEventsView = dayEventsView;
    }
}
