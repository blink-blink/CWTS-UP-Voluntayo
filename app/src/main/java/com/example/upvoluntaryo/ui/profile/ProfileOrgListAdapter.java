package com.example.upvoluntaryo.ui.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.R;
import com.example.upvoluntaryo.objects.Orgs;
import com.example.upvoluntaryo.ui.search.OrgSearchListAdapter;

import java.util.ArrayList;

public class ProfileOrgListAdapter extends RecyclerView.Adapter<ProfileOrgListAdapter.ProfileOrgViewHolder> {

    private ArrayList<Orgs> orgList;
    private OnOrgListener onOrgListener;

    public ProfileOrgListAdapter(ArrayList<Orgs> orgList, OnOrgListener onOrgListener) {
        this.orgList = orgList;
        this.onOrgListener = onOrgListener;
    }

    public void AddOrg(Orgs org){
        orgList.add(org);
        notifyItemInserted(orgList.size());
    }

    public class ProfileOrgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView orgName;
        private TextView orgDetails;
        OnOrgListener onOrgListener;

        public ProfileOrgViewHolder(final View view, OnOrgListener onOrgListener) {
            super(view);
            this.orgName = view.findViewById(R.id.orgName);
            this.orgDetails = view.findViewById(R.id.orgDetails);
            this.onOrgListener = onOrgListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onOrgListener.onOrgClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ProfileOrgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.org_profile_item, parent, false);
        return new ProfileOrgViewHolder(itemView, onOrgListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileOrgViewHolder holder, int position) {
        String orgName = orgList.get(position).getOrgName();
        String orgDetails = orgList.get(position).getOrgDetails();
        holder.orgName.setText(orgName);
        holder.orgDetails.setText(orgDetails);
    }

    @Override
    public int getItemCount() {
        return orgList.size();
    }

    public interface OnOrgListener {
        void onOrgClick(int position);
    }
}
