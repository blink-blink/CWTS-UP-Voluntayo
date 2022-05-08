package com.example.upvoluntaryo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.DBHelper;
import com.example.upvoluntaryo.Event;
import com.example.upvoluntaryo.EventPageActivity;
import com.example.upvoluntaryo.HomeActivity;
import com.example.upvoluntaryo.Orgs;
import com.example.upvoluntaryo.R;

import java.util.ArrayList;

public class ObjectFragment extends Fragment implements EventListAdapter.OnEventListener {
    public static final String ARG_OBJECT = "object";

    // vars
    private ArrayList<Event> eventList = new ArrayList<>();
    private EventListAdapter eventListAdapter;
    private ArrayList<Orgs> orgList = new ArrayList<>();
    private OrgListAdapter orgListAdapter;
    private SearchViewModel searchViewModel;
    private EventViewModel eventViewModel;
    DBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args.getInt(ARG_OBJECT) == 1){

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.itemRecyclerView);

            DB = new DBHelper(getContext());

            eventList = DB.listEvents();

            eventListAdapter = new EventListAdapter( eventList, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(eventListAdapter);

            eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
            eventViewModel.getEventListData(getContext()).observe(getViewLifecycleOwner(), new Observer<ArrayList<Event>>() {
                @Override
                public void onChanged(ArrayList<Event> eventArrayList) {
                    eventListAdapter.updateEventList(DB.listEvents());
                }
            });

        }
        else{

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.itemRecyclerView);

            //test
            orgList.add(new Orgs("Org Name1", "Org Details1"));
            orgList.add(new Orgs("Org Name2", "Org Details2"));
            orgList.add(new Orgs("Org Name3", "Org Details3"));
            orgList.add(new Orgs("Org Name4", "Org Details4"));

            orgListAdapter = new OrgListAdapter(orgList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(orgListAdapter);
        }
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
        searchViewModel.getQuery().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s!=null) {
                    if (orgListAdapter != null) orgListAdapter.getFilter().filter(s);
                    if (eventListAdapter != null) eventListAdapter.getFilter().filter(s);
                }
            }
        });
    }

    @Override
    public void onEventClick(int position) {
        //nav to activity
        Intent intent = new Intent(getContext(), EventPageActivity.class);
        intent.putExtra("eventPageName", eventList.get(position).getEventName());
        intent.putExtra("eventPageDetails", eventList.get(position).getEventDetails());
        startActivity(intent);
    }
}
