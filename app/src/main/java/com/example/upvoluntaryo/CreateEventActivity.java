package com.example.upvoluntaryo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.upvoluntaryo.objects.Event;
import com.example.upvoluntaryo.objects.Orgs;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Intent intent = getIntent();
        int orgId = intent.getIntExtra("orgId",0);

        TextView eventName = (TextView) findViewById(R.id.createEvent_name);
        TextView eventDate = (TextView) findViewById(R.id.createEvent_date);
        TextView eventAddr = (TextView) findViewById(R.id.createEvent_addr);
        TextView eventDesc = (TextView) findViewById(R.id.createEvent_desc);

        Button createEventButton = (Button) findViewById(R.id.createEventButton);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper DB = new DBHelper(CreateEventActivity.this);
                SessionManager sessionManager = new SessionManager(CreateEventActivity.this);

                DB.addEvent(new Event(eventName.getText().toString(),
                        eventDate.getText().toString(),
                        eventAddr.getText().toString(),
                        eventDesc.getText().toString(),
                        orgId,0));

                finish();
            }
        });
    }
}
