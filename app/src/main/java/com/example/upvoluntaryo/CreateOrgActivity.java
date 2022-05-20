package com.example.upvoluntaryo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.upvoluntaryo.objects.Orgs;

public class CreateOrgActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_org);

        TextView orgName = (TextView) findViewById(R.id.createOrg_name);
        TextView orgDesc = (TextView) findViewById(R.id.createOrg_desc);

        Button createOrgButton = (Button) findViewById(R.id.createOrgButton);
        createOrgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper DB = new DBHelper(CreateOrgActivity.this);
                SessionManager sessionManager = new SessionManager(CreateOrgActivity.this);
                Orgs org = new Orgs(orgName.getText().toString(),orgDesc.getText().toString());

                DB.createOrg(sessionManager.getUsersDataFromSession().getUserId(),org);

                finish();
            }
        });
    }

    public void onCreateOrgClicked(View view){

    }
}
