package com.nextevent.Calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextevent.DatabaseHandler;
import com.nextevent.JavaBeans.Event;
import com.nextevent.R;

import java.util.ArrayList;

/**
 * @author Abel Anderson
 * @version 1.0
 * @since 04/04/2020
 *
 *
 * @Last Modified 07/04/2020 by
 * @author Ghaith Darwish
 */

public class CalendarEventRecyclerViewAdapter extends RecyclerView.Adapter<CalendarEventRecyclerViewAdapter.CalendarEventViewHolder> {
    ArrayList<Event> events;
    DatabaseHandler db;
    Context context;

    public CalendarEventRecyclerViewAdapter(ArrayList<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public CalendarEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.calendar_event, null);
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

            titleTextView = itemView.findViewById(R.id.titleTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Creating AlertDialog when deleting an event
                    new AlertDialog.Builder(context).setTitle("Delete")
                            .setMessage("Are you sure you want to delete " + events.get(getLayoutPosition()).getTitle() + " from you calendar?")
                            .setIcon(R.drawable.ic_warning_black_24dp)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Event event = events.get(getAdapterPosition());
                                    // set event setIsAdded to false
                                    event.setIsAdded(false);
                                    db = new DatabaseHandler(context);
                                    // update the database
                                    db.updateEvent(event);
                                    // remove the event from the list
                                    events.remove(event);
                                    notifyItemRemoved(getAdapterPosition());
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            });
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getDateTextView() {
            return dateTextView;
        }

    }
}
