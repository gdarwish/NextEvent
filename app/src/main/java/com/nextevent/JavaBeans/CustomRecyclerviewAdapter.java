package com.nextevent.JavaBeans;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nextevent.DatabaseHandler;
import com.nextevent.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Ghaith Darwish
 * @since 07/04/2020
 * @Last Modified: 08/04/2020
 */
public class CustomRecyclerviewAdapter extends RecyclerView.Adapter<CustomRecyclerviewAdapter.CustomViewHolder> {

    private ArrayList<Event> events;
    private Context context;
    private int id;
    private DatabaseHandler db;
    private boolean showDelete;

    int layout;

    public CustomRecyclerviewAdapter(ArrayList<Event> events, Context context, int id, boolean showDelete) {
        this.events = events;
        this.context = context;
        this.id = id;
        db = new DatabaseHandler(context);
        this.showDelete = showDelete;

        // Create SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        layout = sharedPreferences.getInt("LAYOUT", R.layout.event_item_model);
    }

    @NonNull
    @Override
    public CustomRecyclerviewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new CustomViewHolder(view, id);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerviewAdapter.CustomViewHolder holder, int position) {
        Event event = events.get(position);
        holder.title.setText(event.getTitle());
        holder.date.setText(event.getStart());
        holder.location.setText(event.getCountry());
        // Loading images with Picasso
        Picasso.get().load(event.getImage()).placeholder(R.drawable.placeholder).into(holder.image);
        // Getting rank and setting it to rattingBar
        ratingBarRank(event.getRank(), holder.ratingBar);
        // Getting the labels and setting them in a horizontal scrollview
        for (String label : event.getLabels()) {
            TextView textView = new TextView(context);
            textView.setText(label);
            textView.setPadding(5, 0, 5, 5);
            textView.setBackgroundResource(R.drawable.border);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 10, 0);
            textView.setLayoutParams(params);
            holder.labels.addView(textView);
        }
    }

    /**
     * Setting the ratingBer stars according to the rank
     *
     * @param rank
     * @param ratingBar
     */
    public void ratingBarRank(int rank, RatingBar ratingBar) {
        if (rank > 0 && rank < 20) {
            ratingBar.setRating(1);
        } else if (rank > 20 && rank < 40) {
            ratingBar.setRating(2);
        } else if (rank > 40 && rank < 60) {
            ratingBar.setRating(3);
        } else if (rank > 60 && rank < 80) {
            ratingBar.setRating(4);
        } else if (rank > 80 && rank < 100) {
            ratingBar.setRating(5);
        } else {
            ratingBar.setRating(0);
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    /**
     * Updating the events list
     *
     * @param events
     */
    public void updateList(ArrayList<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected LinearLayout labels;
        protected ImageView image;
        protected TextView title;
        protected TextView date;
        protected TextView location;
        protected RatingBar ratingBar;
        protected ImageButton deleteButton;

        public CustomViewHolder(@NonNull View itemView, final int id) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.date = itemView.findViewById(R.id.date);
            this.image = itemView.findViewById(R.id.image);
            this.location = itemView.findViewById(R.id.location);
            this.labels = itemView.findViewById(R.id.labels);
            this.ratingBar = itemView.findViewById(R.id.ratingBar);
            this.deleteButton = itemView.findViewById(R.id.deleteButton);
            itemView.setOnClickListener(this);

            if (showDelete) {
                deleteButton.setVisibility(View.VISIBLE);
            } else {
                deleteButton.setVisibility(View.GONE);
            }


            // delete events
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    // Creating AlertDialog when deleting an event
                    new AlertDialog.Builder(context).setTitle("Delete")
                            .setMessage("Are you sure you want to delete " + events.get(getLayoutPosition()).getTitle() + "?")
                            .setIcon(R.drawable.ic_warning_black_24dp)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Event event = events.get(getAdapterPosition());
                                    event.setIsSaved(false);

                                    //Check if the event still needs to be in the database
                                    if(event.getIsAdded() == 1) {
                                        db.updateEvent(event);
                                    } else {
                                        db.deleteEvent(event.getId());
                                    }

                                    events.remove(event);
                                    notifyItemRemoved(getAdapterPosition());
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            });
        }

        @Override
        public void onClick(View view) {
            // Create a bundle to send the event object with it
            Bundle arg = new Bundle();
            arg.putParcelable("event", events.get(getAdapterPosition()));
            Navigation.findNavController(view).navigate(id, arg);
        }
    }
}
