package com.example.upvoluntaryo.ui.main;

import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.Event;
import com.example.upvoluntaryo.R;

import java.util.ArrayList;

public class ObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args.getInt(ARG_OBJECT) == 1){

            ArrayList<Event> eventList = new ArrayList<>();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.eventRecyclerView);

            //test
            eventList.add(new Event("Red Check Blood Drive", "eventAddress1", "eventDetails123", R.id.eventImageHeader));
            eventList.add(new Event("Red Check Blood Drive", "eventAddress1", "eventDetails1234", R.id.eventImageHeader));
            eventList.add(new Event("Red Check Blood Drive", "eventAddress1", "eventDetails1234", R.id.eventImageHeader));
            eventList.add(new Event("Red Check Blood Drive", "eventAddress1", "eventDetails123", R.id.eventImageHeader));

            EventListAdapter adapter = new EventListAdapter(eventList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

        }

        //Bundle args = getArguments();
        //((TextView) view.findViewById(R.id.teststring)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
    }
}
