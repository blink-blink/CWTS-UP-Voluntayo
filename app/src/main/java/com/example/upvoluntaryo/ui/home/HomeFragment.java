package com.example.upvoluntaryo.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.Fade;

import com.example.upvoluntaryo.DBHelper;
import com.example.upvoluntaryo.EventPageActivity;
import com.example.upvoluntaryo.R;
import com.example.upvoluntaryo.SearchActivity;
import com.example.upvoluntaryo.SessionManager;
import com.example.upvoluntaryo.databinding.FragmentHomeBinding;
import com.example.upvoluntaryo.objects.Event;
import com.example.upvoluntaryo.ui.search.EventSearchListAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener,HomeListAdapter.OnEventListener {

    private FragmentHomeBinding binding;
    private HomeListAdapter homeListAdapter;
    private ArrayList<Event> eventList;
    DBHelper DB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.searchButton.setOnClickListener(this);

        Fade fade = new Fade();
        View decor = getActivity().getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_container),true);
        fade.excludeTarget(android.R.id.statusBarBackground,true);
        fade.excludeTarget(android.R.id.navigationBarBackground,true);
        getActivity().getWindow().setEnterTransition(fade);
        getActivity().getWindow().setExitTransition(fade);
        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.homeRecyclerView);

        DB = new DBHelper(getContext());

        //change to listFollowedEvents()
        SessionManager sessionManager = new SessionManager(getActivity());
        eventList = DB.listFollowedEvents(sessionManager.getUsersDataFromSession().getUserId());

        homeListAdapter = new HomeListAdapter( eventList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        SessionManager sessionManager = new SessionManager(getActivity());
        eventList = DB.listFollowedEvents(sessionManager.getUsersDataFromSession().getUserId());
        homeListAdapter.updateHomeList(eventList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),binding.searchButton,"searchtransition_animation");
        startActivity(intent, options.toBundle());
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
}