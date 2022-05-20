package com.example.upvoluntaryo.ui.orgPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.CreateEventActivity;
import com.example.upvoluntaryo.DBHelper;
import com.example.upvoluntaryo.OrgPageActivity;
import com.example.upvoluntaryo.R;
import com.example.upvoluntaryo.objects.Event;

import java.util.ArrayList;

public class OrgPageFragmentEvents extends Fragment {

    private int orgId;

    public OrgPageFragmentEvents(int orgId) {
        this.orgId = orgId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orgpagerecview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DBHelper DB = new DBHelper(getContext());

        ArrayList<Event> eventList = DB.listOrgEvents(orgId);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.orgPageRecyclerView);
        EventListAdapter eventListAdapter = new EventListAdapter(eventList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(eventListAdapter);

        Button button = (Button) view.findViewById(R.id.orgPageRVButton);
        button.setText("Add Event");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateEventActivity.class);
                intent.putExtra("orgId", orgId);
                startActivity(intent);
            }
        });
    }
}
