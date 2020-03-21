package com.nextevent.JavaBeans;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.nextevent.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerviewAdapter extends RecyclerView.Adapter<CustomRecyclerviewAdapter.CustomViewHolder> {

    private ArrayList<Event> events;
    private Context context;

    public CustomRecyclerviewAdapter(ArrayList<Event> events, Context context) {
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
        Event event = events.get(position);
        holder.title.setText(event.getTitle());
        holder.date.setText(event.getStart());
        holder.location.setText(event.getCountry());
        Picasso.get().load(event.getImage()).placeholder(R.drawable.placeholder).into(holder.image);
        for (String label:
            event.getLabels()
        ) {
            TextView textView = new TextView(context);
            textView.setText(label);
            textView.setPadding(5,0,5,5);
            textView.setBackgroundResource(R.drawable.border);
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,10,0);
            textView.setLayoutParams(params);
            holder.labels.addView(textView);

        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    public void updateList(ArrayList<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected FlexboxLayout labels;
        protected ImageView image;
        protected TextView title;
        protected TextView date;
        protected TextView location;

         public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.date = itemView.findViewById(R.id.date);
            this.image = itemView.findViewById(R.id.image);
            this.location = itemView.findViewById(R.id.location);
            this.labels = itemView.findViewById(R.id.labels);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle arg = new Bundle();
            arg.putParcelable("event", events.get(getAdapterPosition()));
            Navigation.findNavController(view).navigate(R.id.eventToDetail, arg);
        }
    }
}
