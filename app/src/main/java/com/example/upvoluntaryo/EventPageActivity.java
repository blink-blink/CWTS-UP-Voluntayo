package com.example.upvoluntaryo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EventPageActivity extends AppCompatActivity {
    TextView eventPageName, eventPageDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        //change text
        Intent intent = getIntent();
        eventPageName = (TextView) findViewById(R.id.eventPageName);
        eventPageName.setText(intent.getStringExtra("eventPageName"));
        eventPageDetails = (TextView) findViewById(R.id.eventPageDetails);
        eventPageDetails.setText(intent.getStringExtra("eventPageDetails"));
    }
}