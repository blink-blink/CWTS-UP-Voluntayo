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
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.upvoluntaryo.eventpage.EventPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import jp.wasabeef.blurry.Blurry;

public class EventPageActivity extends AppCompatActivity {
    BitmapDrawable bitmapDrawable;

    @StringRes
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

        //Add Blur takes too much resource
        /*
        blurImage()
        */

        Intent intent = getIntent();
        EventPageAdapter eventPageAdapter = new EventPageAdapter(this,
                intent.getStringExtra("eventPageName"),
                intent.getStringExtra("eventPageDetails"));
        ViewPager2 viewPager = findViewById(R.id.ep_view_pager);
        viewPager.setAdapter(eventPageAdapter);

        TabLayout tabLayout = findViewById(R.id.ep_tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(EVENT_PAGE_TAB_TITLES[position])
        ).attach();

    }

    /*
    private void blurImage(){
        ImageView imageView = (ImageView) findViewById(R.id.eventPageImage);

        MotionLayout.TransitionListener transitionListener = new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
            }

            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

                Bitmap bitmap = Blurry.with(EventPageActivity.this)
                        .radius(1)
                        .sampling(1)
                        .capture(findViewById(R.id.eventPageImage)).get();
                if (bitmapDrawable == null)
                    bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
                else
                    bitmapDrawable.setBitmap(bitmap);
                imageView.setImageDrawable(bitmapDrawable);
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
            }
        };
        MotionLayout motionLayout = (MotionLayout) findViewById(R.id.eventPageMotionLayout);
        motionLayout.addTransitionListener(transitionListener);
    }
     */
}