package com.example.upvoluntaryo.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.objects.Event;
import com.example.upvoluntaryo.R;

import java.util.ArrayList;

public class EventSearchListAdapter extends RecyclerView.Adapter<EventSearchListAdapter.EventViewHolder> implements Filterable {
    private ArrayList<Event> eventSearchList;
    private ArrayList<Event> eventSearchListFull;
    private OnEventListener onEventListener;

    public EventSearchListAdapter(ArrayList<Event> eventSearchList, OnEventListener onEventListener){
        this.eventSearchList = eventSearchList;
        this.onEventListener = onEventListener;
        eventSearchListFull = new ArrayList<>(eventSearchList);
    }

    public void updateEventList(ArrayList<Event> eventList) {
        this.eventSearchList.clear();
        this.eventSearchList.addAll(eventList);
        this.eventSearchListFull.clear();
        this.eventSearchListFull.addAll(eventList);
        notifyDataSetChanged();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView eventName;
        private TextView     eventDetails;
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
    public EventSearchListAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_search_item, parent, false);
        return new EventViewHolder(itemView, onEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventSearchListAdapter.EventViewHolder holder, int position) {
        String eventName = eventSearchList.get(position).getEventName();
        String eventDetails = eventSearchList.get(position).getEventDetails();
        holder.eventName.setText(eventName);

        int maxLength = 100;
        if (eventDetails.length() > maxLength)
            holder.eventDetails.setText(eventDetails.substring(0,maxLength)+"...");
        else
            holder.eventDetails.setText(eventDetails);
    }

    @Override
    public int getItemCount() {
        return eventSearchList.size();
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
                filteredList.addAll(eventSearchListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Event event : eventSearchListFull){
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
            eventSearchList.clear();
            eventSearchList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
