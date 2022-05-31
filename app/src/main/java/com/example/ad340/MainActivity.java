package com.example.ad340;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);

        auth = getAuth();
    }

    @Override
    public void onBackStackChanged() {

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
}
