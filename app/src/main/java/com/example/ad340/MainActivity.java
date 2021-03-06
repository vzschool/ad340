package com.example.ad340;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentManager;

import com.example.ad340.matches.MatchesFragment;
import com.example.ad340.profile.Profile;
import com.example.ad340.profile.ProfileEditFragment;
import com.example.ad340.profile.ProfileFragment;
import com.example.ad340.profile.ProfileViewModel;
import com.example.ad340.settings.SettingsFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);

        auth = getAuth();
        if (auth != null) {
            checkProfile(auth.getUid());
        }
    }

    @Override
    public void onBackStackChanged() {

    }

    public void openProfile(View view) {
        auth = getAuth();
        if (auth != null) {
            checkProfile(auth.getUid());
        }
    }

    public void openMatches(View view) {
        MatchesFragment matchesFragment = new MatchesFragment();
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, matchesFragment)
                .addToBackStack("profileEditFragment")
                .commit();
    }

    public void openSettings(View view) {
        SettingsFragment settingsFragment = new SettingsFragment();
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, settingsFragment)
                .addToBackStack("profileEditFragment")
                .commit();
    }

    /**
     * Check if "auth" is instantiated
     * if not -> start "ActivitySignIn"
     */
    private FirebaseAuth getAuth() {
        auth = Auth.getAuth();
        if (auth == null){
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }
        return Auth.getAuth();
    }

    /**
     * Checks if user has profile in Firebase,
     */
    private void checkProfile(String uid) {
        Consumer<Profile> callback = profile -> {
            if (profile != null) {
                // User does exist, proceed to profile fragment
                openProfileFragment(profile);
            } else {
                // User doesn't exist, proceed to profileEdit fragment
                openProfileEditFragment(uid);
            }
        };
        (new ProfileViewModel()).getProfile(uid, callback);
    }

    public void openProfileEditFragment(String uid) {
        ProfileEditFragment profileEditFragment = new ProfileEditFragment(uid);
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, profileEditFragment)
                .addToBackStack("profileEditFragment")
                .commit();
    }

    public void openProfileEditFragment(Profile profile) {
        ProfileEditFragment profileEditFragment = new ProfileEditFragment(profile);
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, profileEditFragment)
                .addToBackStack("profileEditFragment")
                .commit();
    }

    public void openProfileFragment(Profile profile) {
        Log.i(TAG, "open profile fragment");
        ProfileFragment profileFragment = new ProfileFragment(profile);
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, profileFragment)
                .commit();
    }
}
