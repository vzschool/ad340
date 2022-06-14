package com.example.ad340.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ad340.MainActivity;
import com.example.ad340.R;


public class ProfileEditFragment extends Fragment {
    private final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private Profile profile;
    private final String uid;
    private EditText editTextName;
    private EditText editTextOccupation;
    private EditText editTextAbout;
    private TextView textViewDOB;
    private int yyyy;
    private int mm;
    private int dd;

    public ProfileEditFragment(String uid) {
        this.uid = uid;
    }
    public ProfileEditFragment(Profile profile) {
        this.profile = profile;
        this.uid = profile.getUid();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_edit, container, false);

        fragmentManager = getParentFragmentManager();

        editTextName = view.findViewById(R.id.editTextName);
        editTextOccupation = view.findViewById(R.id.editTextOccupation);
        editTextAbout = view.findViewById(R.id.editTextAbout);
        textViewDOB = view.findViewById(R.id.textViewDOB);
        Button buttonDOB = view.findViewById(R.id.buttonDOB);
        Button buttonSaveProfile = view.findViewById(R.id.buttonSaveProfile);

        if (profile != null) {
            editTextName.setText(profile.getName());
            editTextOccupation.setText(profile.getOccupation());
            editTextAbout.setText(profile.getAbout());
            String dob = profile.getYYYY() + "/" +
                         profile.getMM() + "/" +
                         profile.getDD();
            textViewDOB.setText(dob);
            yyyy = profile.getYYYY();
            mm = profile.getMM();
            dd = profile.getDD();
        }

        buttonDOB.setOnClickListener(view1 -> {
            DatePickerFragment datePickerFragment = new DatePickerFragment();
            datePickerFragment.show(fragmentManager, "date picker");
            fragmentManager.setFragmentResultListener("requestKey", ProfileEditFragment.this, (requestKey, result) -> {
                textViewDOB.setText(result.getString("DOB"));
                yyyy = result.getInt("yyyy");
                mm = result.getInt("mm");
                dd = result.getInt("dd");
            });
        });

        buttonSaveProfile.setOnClickListener(view12 -> {
            String name = String.valueOf(editTextName.getText());
            String occupation = String.valueOf(editTextOccupation.getText());
            String about = String.valueOf(editTextAbout.getText());
            if (name.equals("") || occupation.equals("") || about.equals("")) {
                Toast toast = Toast.makeText(getActivity(), "empty fields!", Toast.LENGTH_LONG);
                toast.show();
                Log.i(TAG, "empty fields!");
                return;
            }
            Profile profile = new Profile(uid, name, occupation, about, yyyy, mm, dd);
            if (profile.getAge() < 18) {
                Toast toast = Toast.makeText(getActivity(), "you are too young!", Toast.LENGTH_LONG);
                toast.show();
                Log.i(TAG, "you are too young!");
                return;
            }
            (new ProfileViewModel()).updateProfile(profile);
            ((MainActivity)getActivity()).openProfileFragment(profile);
            fragmentManager.beginTransaction().remove(this).commit();
        });

        return view;
    }
}
