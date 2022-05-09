package com.example.upvoluntaryo.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class EventPageAdapter extends FragmentStateAdapter {
    private String eventName, eventDetails;
    public EventPageAdapter(FragmentActivity fragment,String eventName, String eventDetails) {
        super(fragment);
        this.eventName = eventName;
        this.eventDetails = eventDetails;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //to be changed, assign different fragments for each tab
        Fragment fragment = EventPageFragment.newInstance(eventName, eventDetails);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
