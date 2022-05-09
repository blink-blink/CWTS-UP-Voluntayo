package com.example.upvoluntaryo;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.upvoluntaryo.ui.main.EventPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class EventPageActivity extends AppCompatActivity {
    @StringRes
    private static final String[] EVENT_PAGE_TAB_TITLES = new String[]{"Description", "Rules", "Project POC", "About Org"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        Intent intent = getIntent();
        EventPageAdapter eventPageAdapter = new EventPageAdapter(this,
                intent.getStringExtra("eventPageName"),
                intent.getStringExtra("eventPageDetails"));
        ViewPager2 viewPager = findViewById(R.id.ep_view_pager);
        viewPager.setAdapter(eventPageAdapter);

        TabLayout tabLayout = findViewById(R.id.ep_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Description"));
        tabLayout.addTab(tabLayout.newTab().setText("Rules"));
        tabLayout.addTab(tabLayout.newTab().setText("Project POC"));
        tabLayout.addTab(tabLayout.newTab().setText("About Org"));

    }
}