package com.example.upvoluntaryo.ui.orgPage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OrgPageAdapter extends FragmentStateAdapter {

    private int orgId;

    public OrgPageAdapter(@NonNull FragmentActivity fragmentActivity, int orgId) {
        super(fragmentActivity);
        this.orgId = orgId;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new OrgPageFragmentDetails();
        else if (position == 1) return new OrgPageFragmentEvents(orgId);
        return new OrgPageFragmentMembers(orgId);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
