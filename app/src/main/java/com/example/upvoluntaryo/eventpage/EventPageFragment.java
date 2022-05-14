package com.example.upvoluntaryo.eventpage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.upvoluntaryo.R;

public class EventPageFragment extends Fragment {
    private static final String ARG_PARAM1 = "eventName";
    private static final String ARG_PARAM2 = "eventDetails";

    private String mParam1;
    private String mParam2;

    public static EventPageFragment newInstance(String param1, String param2) {
        EventPageFragment fragment = new EventPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView eventPageName = (TextView) view.findViewById(R.id.eventPageName);
        eventPageName.setText(mParam1);
        TextView eventPageDetails = (TextView) view.findViewById(R.id.eventPageDetails);
        eventPageDetails.setText(mParam2);
    }
}