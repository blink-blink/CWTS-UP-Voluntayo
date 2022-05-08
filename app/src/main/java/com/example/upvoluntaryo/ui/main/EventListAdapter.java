package com.example.upvoluntaryo.ui.main;

import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.Event;
import com.example.upvoluntaryo.R;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.MyViewHolder> {
    private ArrayList<Event> eventList;

    public EventListAdapter(ArrayList<Event> eventList){
        this.eventList = eventList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView eventName;
        private TextView eventDetails;

        public MyViewHolder(final View view){
            super(view);
            eventName = view.findViewById(R.id.eventName);
            eventDetails = view.findViewById(R.id.eventDetails);
        }
    }

    @NonNull
    @Override
    public EventListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.MyViewHolder holder, int position) {
        String eventName = eventList.get(position).getEventName();
        String eventDetails = eventList.get(position).getEventDetails();
        holder.eventName.setText(eventName);
        holder.eventDetails.setText(eventDetails);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
