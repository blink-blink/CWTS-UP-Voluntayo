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

    public OrgSearchListAdapter(ArrayList<Orgs> orgSearchList) {
        this.orgSearchList = orgSearchList;
        orgSearchListFull = new ArrayList<>(orgSearchList);
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
    public OrgSearchListAdapter.OrgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.org_item, parent, false);
        return new OrgViewHolder(itemView);
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
