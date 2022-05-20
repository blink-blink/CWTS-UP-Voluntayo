package com.example.upvoluntaryo.ui.orgPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.upvoluntaryo.R;

public class OrgPageFragmentDetails extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orgpagedetails, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView orgDetails = (TextView) view.findViewById(R.id.orgPageTextDetails);
        orgDetails.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi mauris enim, egestas in hendrerit a, lacinia ac lacus. Morbi imperdiet, est ut pulvinar ornare, sapien lacus consequat erat, ac vehicula orci nulla eu metus. Maecenas est enim, dapibus vitae tempor id, sollicitudin non est. Mauris a eros lacinia, ullamcorper nunc vel, facilisis lectus. Nullam dictum dignissim ipsum, at finibus libero semper id. Pellentesque leo metus, luctus vel libero vel, rhoncus pharetra magna. Fusce urna elit, ultrices at felis a, vulputate iaculis magna. Pellentesque scelerisque sem a tellus accumsan efficitur.");
    }
}
