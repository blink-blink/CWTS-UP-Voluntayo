package com.example.upvoluntaryo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OrgPageActivity extends AppCompatActivity {
    DBHelper DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_page);

        DB = DB = new DBHelper(this);
        Intent intent = getIntent();

        TextView orgName = (TextView) findViewById(R.id.op_orgName);
        orgName.setText(DB.getOrgData(intent.getIntExtra("orgId",0),1));
    }
}
