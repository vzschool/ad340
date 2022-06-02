package com.example.ad340.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ad340.MainActivity;
import com.example.ad340.R;

public class ProfileFragment extends Fragment {
    private final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private final Profile profile;
    private TextView textViewName;
    private TextView textViewOccupation;
    private TextView textViewAbout;
    private TextView textViewAge;

    /**
     * Profile is provided, we have to update Firestore
     * @param profile build fragment from profile
     */
    public ProfileFragment (Profile profile) {
        this.profile = profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fragmentManager = getParentFragmentManager();

        textViewName = view.findViewById(R.id.textViewName);
        textViewOccupation = view.findViewById(R.id.textViewOccupation);
        textViewAbout = view.findViewById(R.id.textViewAbout);
        textViewAge = view.findViewById(R.id.textViewAge);
        Button buttonOpenProfileEdit = view.findViewById(R.id.buttonOpenProfileEdit);

        updateProfile(profile);

        buttonOpenProfileEdit.setOnClickListener(view1 -> {
            ((MainActivity)getActivity()).openProfileEditFragment(profile);
            fragmentManager.beginTransaction().remove(ProfileFragment.this).commit();
        });

        return view;
    }

    public void updateProfile(Profile profile) {
        textViewName.setText(profile.getName());
        textViewOccupation.setText(profile.getOccupation());
        textViewAbout.setText(profile.getAbout());
        textViewAge.setText(String.valueOf(profile.getAge()));
    }
}
