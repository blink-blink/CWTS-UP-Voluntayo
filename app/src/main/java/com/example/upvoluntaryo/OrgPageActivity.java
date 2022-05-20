package com.example.upvoluntaryo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.upvoluntaryo.ui.orgPage.OrgPageAdapter;
import com.example.upvoluntaryo.ui.profile.ProfileTabAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OrgPageActivity extends AppCompatActivity {
    private static final String[] ORG_PAGE_TAB_TITLES = new String[]{"About", "Events", "Members"};

    DBHelper DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page);

        DB = new DBHelper(this);
        Intent intent = getIntent();
        int orgId = intent.getIntExtra("orgId",0);

        TextView orgName = (TextView) findViewById(R.id.op_orgName);
        orgName.setText(DB.getOrgData(orgId,1));

        OrgPageAdapter orgPageAdapter = new OrgPageAdapter(this, orgId);
        ViewPager2 viewPager = findViewById(R.id.org_page_view_pager);
        viewPager.setAdapter(orgPageAdapter);

        TabLayout tabLayout = findViewById(R.id.org_page_tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(ORG_PAGE_TAB_TITLES[position])
        ).attach();
    }
}
