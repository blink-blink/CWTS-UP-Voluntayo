package com.example.upvoluntaryo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.Event;
import com.example.upvoluntaryo.R;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> implements Filterable {
    private ArrayList<Event> eventList;
    private ArrayList<Event> eventListFull;
    private OnEventListener onEventListener;

    public EventListAdapter(ArrayList<Event> eventList, OnEventListener onEventListener){
        this.eventList = eventList;
        this.onEventListener = onEventListener;
        eventListFull = new ArrayList<>(eventList);
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView eventName;
        private TextView eventDetails;
        OnEventListener onEventListener;

        public EventViewHolder(final View view, OnEventListener onEventListener){
            super(view);
            eventName = view.findViewById(R.id.eventName);
            eventDetails = view.findViewById(R.id.eventDetails);
            this.onEventListener = onEventListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onEventListener.onEventClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(itemView, onEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.EventViewHolder holder, int position) {
        String eventName = eventList.get(position).getEventName();
        String eventDetails = eventList.get(position).getEventDetails();
        holder.eventName.setText(eventName);
        holder.eventDetails.setText(eventDetails);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public interface OnEventListener {
        void onEventClick(int position);
    }

    @Override
    public Filter getFilter() {
        return eventListFilter;
    }

    private Filter eventListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0 ){
                filteredList.addAll(eventListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Event event : eventListFull){
                    if (event.getEventName().toLowerCase().contains(filterPattern) ||
                            event.getEventDetails().toLowerCase().contains(filterPattern)){
                        filteredList.add(event);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            eventList.clear();
            eventList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
