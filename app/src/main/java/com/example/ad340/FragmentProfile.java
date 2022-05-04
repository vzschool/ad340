package com.example.ad340;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FragmentProfile extends Fragment {
    private String TAG = FragmentProfile.class.getSimpleName();

    private TextView name;
    private TextView occupation;
    private TextView description;
    private TextView age;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach()");
    }

    @Override
    public void onCreate(@NotNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Bundle b = getArguments();

        name = view.findViewById(R.id.textViewName);
        occupation = view.findViewById(R.id.textViewOccupation);
        description = view.findViewById(R.id.textViewDescription);
        age = view.findViewById(R.id.textViewAge);

        if (b != null) {
            name.setText(b.getString(Constants.NAME_KEY));
            occupation.setText(b.getString(Constants.OCCUPATION_KEY));
            description.setText(b.getString(Constants.DESCRIPTION_KEY));
            age.setText(b.getString(Constants.AGE_KEY));
        }

        return view;
    }
}
