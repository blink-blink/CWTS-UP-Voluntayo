package com.example.upvoluntaryo;

import android.app.Instrumentation;
import android.os.Bundle;

import com.example.upvoluntaryo.objects.Event;
import com.example.upvoluntaryo.ui.search.SearchAdapter;
import com.example.upvoluntaryo.ui.search.EventSearchListViewModel;
import com.example.upvoluntaryo.ui.search.SearchViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import android.transition.Fade;
import androidx.viewpager2.widget.ViewPager2;

import android.view.View;

import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Random;

public class SearchActivity extends AppCompatActivity {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // window animation
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_container),true);
        fade.excludeTarget(android.R.id.statusBarBackground,true);
        fade.excludeTarget(android.R.id.navigationBarBackground,true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        SearchAdapter searchAdapter = new SearchAdapter(this);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(searchAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();

        //SearchView
        SearchViewModel searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        SearchView searchItem = (SearchView) findViewById(R.id.searchItem);
        searchItem.setIconified(false);
        //searchItem.requestFocus();

        searchItem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchViewModel.setQuery(newText);
                return false;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Event Added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                DBHelper DB = new DBHelper(getApplicationContext());

                //test
                Random r = new Random();
                switch (r.nextInt(5)){
                    case 0:
                        DB.addEvent(new Event("Red Check Blood Drive",
                                "middle of nowhere",
                                "sample event details",
                                0,0));
                        break;
                    case 1:
                        DB.addEvent(new Event("Blue Check Blood Drive",
                                "middle of nowhere",
                                "sample event details2",
                                0,0));
                        break;
                    case 2:
                        DB.addEvent(new Event("Green Check Blood Drive",
                                "middle of nowhere",
                                "sample event details2",
                                0,0));
                        break;
                    case 3:
                        DB.addEvent(new Event("Yellow Check Blood Drive",
                                "middle of nowhere",
                                "sample event details2",
                                0,0));
                        break;
                    case 4:
                        DB.addEvent(new Event("Orange Check Blood Drive",
                                "middle of nowhere",
                                "sample event details2",
                                0,0));
                        break;
                }

                //DB.clearEvents();
                //EventSearchListViewModel
                EventSearchListViewModel eventSearchListViewModel = new ViewModelProvider(SearchActivity.this).get(EventSearchListViewModel.class);
                eventSearchListViewModel.setEventListData(DB.listEvents());
            }
        });
    }

    @Override
    protected void onStop() {
        if (!isFinishing())
            new Instrumentation().callActivityOnSaveInstanceState(this, new Bundle());
        super.onStop();
    }
}