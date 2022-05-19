package com.example.upvoluntaryo;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.upvoluntaryo.eventpage.EventPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jgabrielfreitas.core.BlurImageView;

import jp.wasabeef.blurry.Blurry;

public class EventPageActivity extends AppCompatActivity {
    //BitmapDrawable bitmapDrawable;
    DBHelper DB;

    //@StringRes
    private static final String[] EVENT_PAGE_TAB_TITLES = new String[]{"Description", "Rules", "Project POC", "About Org"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        // window animation
        Slide slide = new Slide(Gravity.BOTTOM);
        slide.setDuration(1000);
        slide.excludeTarget(android.R.id.statusBarBackground,true);
        slide.excludeTarget(android.R.id.navigationBarBackground,true);
        getWindow().setEnterTransition(slide);
        getWindow().setExitTransition(slide);

        //Add Blur
        blurImage();

        Intent intent = getIntent();
        EventPageAdapter eventPageAdapter = new EventPageAdapter(this,
                intent.getIntExtra("eventId",0),
                intent.getStringExtra("eventPageName"),
                intent.getStringExtra("eventPageDetails"));
        ViewPager2 viewPager = findViewById(R.id.ep_view_pager);
        viewPager.setAdapter(eventPageAdapter);

        TabLayout tabLayout = findViewById(R.id.ep_tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(EVENT_PAGE_TAB_TITLES[position])
        ).attach();

        Button followButton = findViewById(R.id.ep_follow_button);
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(EventPageActivity.this);
                //follow
                DB = new DBHelper(EventPageActivity.this);
                DB.followEvent(sessionManager.getUsersDataFromSession().getUserId(),intent.getIntExtra("eventId",0));
            }
        });
    }

    private void blurImage(){
        BlurImageView blurImageView = (BlurImageView) findViewById(R.id.eventPageImage);

        MotionLayout.TransitionListener transitionListener = new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
            }

            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
                blurImageView.setBlur((int) (progress*10));
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                if (currentId == R.id.start)
                    blurImageView.setBlur(0);
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
            }
        };
        MotionLayout motionLayout = (MotionLayout) findViewById(R.id.eventPageMotionLayout);
        motionLayout.addTransitionListener(transitionListener);
    }
}