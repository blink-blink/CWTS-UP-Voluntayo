package com.example.upvoluntaryo.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.DBHelper;
import com.example.upvoluntaryo.OrgPageActivity;
import com.example.upvoluntaryo.objects.Event;
import com.example.upvoluntaryo.EventPageActivity;
import com.example.upvoluntaryo.objects.Orgs;
import com.example.upvoluntaryo.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements EventSearchListAdapter.OnEventListener, OrgSearchListAdapter.OnOrgListener {
    public static final String ARG_OBJECT = "object";

    // vars
    private ArrayList<Event> eventList = new ArrayList<>();
    private EventSearchListAdapter eventSearchListAdapter;
    private ArrayList<Orgs> orgList = new ArrayList<>();
    private OrgSearchListAdapter orgSearchListAdapter;
    private SearchViewModel searchViewModel;
    private EventSearchListViewModel eventSearchListViewModel;
    private OrgSearchListViewModel orgSearchListViewModel;
    DBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args.getInt(ARG_OBJECT) == 1){

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.searchRecyclerView);

            DB = new DBHelper(getContext());

            eventList = DB.listEvents();

            eventSearchListAdapter = new EventSearchListAdapter( eventList, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(eventSearchListAdapter);
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

            eventSearchListViewModel = new ViewModelProvider(requireActivity()).get(EventSearchListViewModel.class);
            eventSearchListViewModel.getEventListData(getContext()).observe(getViewLifecycleOwner(), new Observer<ArrayList<Event>>() {
                @Override
                public void onChanged(ArrayList<Event> eventArrayList) {
                    eventSearchListAdapter.updateEventList(DB.listEvents());
                    if (searchViewModel.getQuery().getValue() != null)
                        if (eventSearchListAdapter != null) eventSearchListAdapter.getFilter().filter(searchViewModel.getQuery().getValue());
                }
            });

        }
        else{

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.searchRecyclerView);

            DB = new DBHelper(getContext());

            orgList = DB.listOrgs();

            orgSearchListAdapter = new OrgSearchListAdapter(orgList, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(orgSearchListAdapter);

            orgSearchListViewModel = new ViewModelProvider(requireActivity()).get(OrgSearchListViewModel.class);
            orgSearchListViewModel.getEventListData(getContext()).observe(getViewLifecycleOwner(), new Observer<ArrayList<Orgs>>() {
                @Override
                public void onChanged(ArrayList<Orgs> orgsArrayList) {
                    orgSearchListAdapter.updateOrgList(DB.listOrgs());
                    if (searchViewModel.getQuery().getValue() != null)
                        if (orgSearchListAdapter != null) orgSearchListAdapter.getFilter().filter(searchViewModel.getQuery().getValue());
                }
            });
        }
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
        searchViewModel.getQuery().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s!=null) {
                    if (orgSearchListAdapter != null) orgSearchListAdapter.getFilter().filter(s);
                    if (eventSearchListAdapter != null) eventSearchListAdapter.getFilter().filter(s);
                }
            }
        });
    }

    @Override
    public void onEventClick(int position) {
        //nav to activity
        Intent intent = new Intent(getContext(), EventPageActivity.class);
        intent.putExtra("eventId", eventList.get(position).getEventId());
        intent.putExtra("eventPageName", eventList.get(position).getEventName());
        intent.putExtra("eventPageDetails", eventList.get(position).getEventDetails());
        startActivity(intent);
    }

    @Override
    public void onOrgClick(int position) {
        Intent intent = new Intent(getContext(), OrgPageActivity.class);
        intent.putExtra("orgId", orgList.get(position).getOrgId());
        startActivity(intent);
    }
}
