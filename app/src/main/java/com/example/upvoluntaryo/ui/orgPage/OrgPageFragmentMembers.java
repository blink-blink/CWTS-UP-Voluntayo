package com.example.upvoluntaryo.ui.orgPage;

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

import com.example.upvoluntaryo.DBHelper;
import com.example.upvoluntaryo.R;
import com.example.upvoluntaryo.SessionManager;
import com.example.upvoluntaryo.objects.Users;
import com.example.upvoluntaryo.ui.profile.ProfileOrgListAdapter;

import java.util.ArrayList;

public class OrgPageFragmentMembers extends Fragment {

    private ArrayList<Users> memberList;
    private int orgId;

    public OrgPageFragmentMembers(int orgId) {
        this.orgId = orgId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orgpagerecview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SessionManager sessionManager = new SessionManager(getContext());
        DBHelper DB = new DBHelper(getContext());

        memberList = DB.listOrgMembers(orgId);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.orgPageRecyclerView);
        MemberListAdapter memberListAdapter = new MemberListAdapter(memberList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(memberListAdapter);
    }
}
