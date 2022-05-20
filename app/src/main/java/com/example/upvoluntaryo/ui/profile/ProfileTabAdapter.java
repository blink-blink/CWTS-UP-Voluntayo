package com.example.upvoluntaryo.ui.profile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ProfileTabAdapter extends FragmentStateAdapter {
    public ProfileTabAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new ProfileTabAboutFragment();
        return new ProfileTabOrgsFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
