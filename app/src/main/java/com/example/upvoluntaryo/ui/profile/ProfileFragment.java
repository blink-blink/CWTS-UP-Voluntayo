package com.example.upvoluntaryo.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.upvoluntaryo.DBHelper;
import com.example.upvoluntaryo.R;
import com.example.upvoluntaryo.RegisterActivity;
import com.example.upvoluntaryo.SessionManager;
import com.example.upvoluntaryo.databinding.FragmentProfileBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ProfileFragment extends Fragment {

    private static final String[] PROFILE_PAGE_TAB_TITLES = new String[]{"About", "Orgs"};

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        DBHelper DB = new DBHelper(getContext());
        SessionManager sessionManager = new SessionManager(getContext());

        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView userProfileName = (TextView) root.findViewById(R.id.userProfileName);
        userProfileName.setText(DB.getUserData(sessionManager.getUsersDataFromSession().getUsername(),1));

        ProfileTabAdapter profileTabAdapter = new ProfileTabAdapter(this);
        ViewPager2 viewPager = root.findViewById(R.id.profile_view_pager);
        viewPager.setAdapter(profileTabAdapter);

        TabLayout tabLayout = root.findViewById(R.id.profile_tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(PROFILE_PAGE_TAB_TITLES[position])
        ).attach();


        Button logoutButton = (Button) root.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(getActivity());
                sessionManager.logoutUserFromSession();
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}