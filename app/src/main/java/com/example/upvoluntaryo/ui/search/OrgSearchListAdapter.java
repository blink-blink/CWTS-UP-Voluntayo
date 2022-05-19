package com.example.upvoluntaryo.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.objects.Orgs;
import com.example.upvoluntaryo.R;

import java.util.ArrayList;

public class OrgSearchListAdapter extends RecyclerView.Adapter<OrgSearchListAdapter.OrgViewHolder> implements Filterable {
    private ArrayList<Orgs> orgSearchList;
    private ArrayList<Orgs> orgSearchListFull;
    private OnOrgListener onOrgListener;

    public OrgSearchListAdapter(ArrayList<Orgs> orgSearchList, OnOrgListener onOrgListener) {
        this.orgSearchList = orgSearchList;
        this.onOrgListener = onOrgListener;
        orgSearchListFull = new ArrayList<>(orgSearchList);
    }

    public void updateOrgList(ArrayList<Orgs> orgList) {
        this.orgSearchList.clear();
        this.orgSearchList.addAll(orgList);
        this.orgSearchListFull.clear();
        this.orgSearchListFull.addAll(orgList);
        notifyDataSetChanged();
    }

    public class OrgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView orgName;
        private TextView orgDetails;
        OnOrgListener onOrgListener;

        public OrgViewHolder(final View view, OnOrgListener onOrgListener) {
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
    public OrgSearchListAdapter.OrgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.org_search_item, parent, false);
        return new OrgViewHolder(itemView, onOrgListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrgViewHolder holder, int position) {
        String orgName = orgSearchList.get(position).getOrgName();
        String orgDetails = orgSearchList.get(position).getOrgDetails();
        holder.orgName.setText(orgName);
        holder.orgDetails.setText(orgDetails);
    }

    @Override
    public int getItemCount() {
        return orgSearchList.size();
    }

    public interface OnOrgListener {
        void onOrgClick(int position);
    }

    @Override
    public Filter getFilter() {
        return orgListFilter;
    }

    private Filter orgListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0 ){
                filteredList.addAll(orgSearchListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Orgs org : orgSearchListFull){
                    if (org.getOrgName().toLowerCase().contains(filterPattern) ||
                            org.getOrgDetails().toLowerCase().contains(filterPattern)){
                        filteredList.add(org);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            orgSearchList.clear();
            orgSearchList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
