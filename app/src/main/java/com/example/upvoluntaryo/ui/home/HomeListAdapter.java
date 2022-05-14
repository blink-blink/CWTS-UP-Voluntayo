package com.example.upvoluntaryo.ui.home;

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

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder> {
    public ArrayList<Event> eventList;
    private OnEventListener onEventListener;

    public HomeListAdapter(ArrayList<Event> eventList, OnEventListener onEventListener) {
        this.eventList = eventList;
        this.onEventListener = onEventListener;
    }

    public class HomeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView eventName;
        private OnEventListener onEventListener;

        public HomeListViewHolder(final View view,OnEventListener onEventListener) {
            super(view);
            this.eventName = view.findViewById(R.id.home_eventname);
            this.onEventListener = onEventListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onEventListener.onEventClick(getAdapterPosition());
        }
    }

    public void updateHomeList(ArrayList<Event> newEventList){
        this.eventList.clear();
        this.eventList.addAll(newEventList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_event_item, parent, false);
        return new HomeListAdapter.HomeListViewHolder(itemView,onEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListViewHolder holder, int position) {
        String eventName = eventList.get(position).getEventName();
        holder.eventName.setText(eventName);
    }

    @Override
    public int getItemCount() { return eventList.size(); }

    public interface OnEventListener {
        void onEventClick(int position);
    }
}
