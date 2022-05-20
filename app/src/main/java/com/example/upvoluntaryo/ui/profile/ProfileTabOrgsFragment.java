package com.example.upvoluntaryo.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.CreateOrgActivity;
import com.example.upvoluntaryo.DBHelper;
import com.example.upvoluntaryo.OrgPageActivity;
import com.example.upvoluntaryo.R;
import com.example.upvoluntaryo.SessionManager;
import com.example.upvoluntaryo.objects.Orgs;
import com.example.upvoluntaryo.ui.search.OrgSearchListAdapter;

import java.util.ArrayList;

public class ProfileTabOrgsFragment extends Fragment implements View.OnClickListener, ProfileOrgListAdapter.OnOrgListener{

    private ArrayList<Orgs> orgList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profileorglist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SessionManager sessionManager = new SessionManager(getContext());
        DBHelper DB = new DBHelper(getContext());

        orgList = DB.listUserOrgs(sessionManager.getUsersDataFromSession().getUserId());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.profileOrgRecyclerView);
        ProfileOrgListAdapter profileOrgListAdapter= new ProfileOrgListAdapter(orgList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(profileOrgListAdapter);

        Button createOrgButton = (Button) view.findViewById(R.id.profileCreateOrgButton);
        createOrgButton.setOnClickListener(this);
    }

    @Override
    public void onOrgClick(int position) {
        Intent intent = new Intent(getContext(), OrgPageActivity.class);
        intent.putExtra("orgId", orgList.get(position).getOrgId());
        startActivity(intent);
    }

    //Create Org Button
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), CreateOrgActivity.class);
        startActivity(intent);
    }
}
