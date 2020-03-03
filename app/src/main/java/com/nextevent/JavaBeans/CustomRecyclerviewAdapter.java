package com.nextevent.JavaBeans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextevent.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerviewAdapter extends RecyclerView.Adapter<CustomRecyclerviewAdapter.CustomViewHolder> {

    private ArrayList<EventModel> events;
    private Context context;

    public CustomRecyclerviewAdapter(ArrayList<EventModel> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomRecyclerviewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item_model, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerviewAdapter.CustomViewHolder holder, int position) {
        EventModel eventModel = events.get(position);
        holder.title.setText(eventModel.getTitle());
        holder.description.setText(eventModel.getDescription());
        Picasso.get().load(eventModel.getImageUrl()).placeholder(R.drawable.placeholder).into(holder.image);
//        Picasso.with(context).load(eventModel.getImageUrl()).placeholder(R.drawable.ic_event_available_black_24dp).into(holder.image);
//         Picasso.with(this).load("www.journaldev.com").placeholder(R.drawable.placeholder).into(imageView);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView image;
        protected TextView title;
        protected TextView description;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.description = itemView.findViewById(R.id.description);
            this.image = itemView.findViewById(R.id.image);
        }
    }
}
