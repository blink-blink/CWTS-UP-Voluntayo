package com.example.upvoluntaryo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.Orgs;
import com.example.upvoluntaryo.R;

import java.util.ArrayList;

public class OrgListAdapter extends RecyclerView.Adapter<OrgListAdapter.OrgViewHolder> {
    private ArrayList<Orgs> orgList;

    public OrgListAdapter(ArrayList<Orgs> orgList) {
        this.orgList = orgList;
    }

    public class OrgViewHolder extends RecyclerView.ViewHolder{
        private TextView orgName;
        private TextView orgDetails;

        public OrgViewHolder(final View view) {
            super(view);
            this.orgName = view.findViewById(R.id.orgName);
            this.orgDetails = view.findViewById(R.id.orgDetails);
        }
    }

    @NonNull
    @Override
    public OrgListAdapter.OrgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.org_item, parent, false);
        return new OrgViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrgViewHolder holder, int position) {
        String orgName = orgList.get(position).getOrgName();
        String orgDetails = orgList.get(position).getOrgDetails();
        holder.orgName.setText(orgName);
        holder.orgDetails.setText(orgDetails);
    }

    @Override
    public int getItemCount() {
        return orgList.size();
    }
}
