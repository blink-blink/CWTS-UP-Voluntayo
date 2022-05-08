package com.example.upvoluntaryo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.Event;
import com.example.upvoluntaryo.Orgs;
import com.example.upvoluntaryo.R;

import java.util.ArrayList;

public class OrgListAdapter extends RecyclerView.Adapter<OrgListAdapter.OrgViewHolder> implements Filterable {
    private ArrayList<Orgs> orgList;
    private ArrayList<Orgs> orgListFull;

    public OrgListAdapter(ArrayList<Orgs> orgList) {
        this.orgList = orgList;
        orgListFull = new ArrayList<>(orgList);
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

    @Override
    public Filter getFilter() {
        return orgListFilter;
    }

    private Filter orgListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0 ){
                filteredList.addAll(orgListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Orgs org : orgListFull){
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
            orgList.clear();
            orgList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
