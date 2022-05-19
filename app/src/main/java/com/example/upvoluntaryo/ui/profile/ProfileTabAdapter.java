package com.example.upvoluntaryo.ui.profile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.upvoluntaryo.ui.search.SearchFragment;

public class ProfileTabAdapter extends FragmentStateAdapter {
    public ProfileTabAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new ProfileTabFragment();
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
