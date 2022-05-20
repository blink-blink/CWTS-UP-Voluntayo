package com.example.upvoluntaryo.ui.orgPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.R;
import com.example.upvoluntaryo.objects.Event;
import com.example.upvoluntaryo.ui.search.EventSearchListAdapter;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private ArrayList<Event> eventList;

    public EventListAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{
        private TextView eventName;
        private TextView eventDatePlace;
        private TextView eventDetails;

        public EventViewHolder(final View view) {
            super(view);
            eventName = view.findViewById(R.id.eventName);
            eventDatePlace = view.findViewById(R.id.eventDatePlace);
            eventDetails = view.findViewById(R.id.eventDetails);
        }
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.org_page_event_item, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        String eventName = eventList.get(position).getEventName();
        String eventDatePlace = eventList.get(position).getEventDate()+"│"+eventList.get(position).getEventAddress();
        String eventDetails = eventList.get(position).getEventDetails();
        holder.eventName.setText(eventName);
        holder.eventDatePlace.setText(eventDatePlace);
        holder.eventDetails.setText(eventDetails);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
