package com.example.upvoluntaryo.ui.orgPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upvoluntaryo.R;
import com.example.upvoluntaryo.objects.Users;
import com.example.upvoluntaryo.ui.profile.ProfileOrgListAdapter;

import java.util.ArrayList;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {

    public ArrayList<Users> memberList;

    public MemberListAdapter(ArrayList<Users> memberList) {
        this.memberList = memberList;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder{
        private TextView memberName;
        private TextView memberRole;

        public MemberViewHolder(final View view) {
            super(view);
            this.memberName = view.findViewById(R.id.memberName);
            this.memberRole = view.findViewById(R.id.memberRole);
        }
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.org_page_member_item, parent, false);
        return new MemberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        String memberName = memberList.get(position).getFullName();
        String memberRole = "member";
        holder.memberName.setText(memberName);
        holder.memberRole.setText(memberRole);
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }
}
