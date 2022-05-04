package com.example.ad340;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    private String TAG = FragmentProfile.class.getSimpleName();

    FragmentManager fragmentManager;

    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextOccupation;
    private Button buttonDOB;
    private DatePickerDialog datePickerDialog;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);

        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextOccupation = findViewById(R.id.editTextOccupation);
        buttonDOB = findViewById(R.id.buttonDOB);

        java.util.Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        datePickerDialog = new DatePickerDialog(this, (datePicker, year1, month1, day1) -> {
            year = year1;
            month = month1 + 1;
            day = day1;
            String date1 = month + "/" + day + "/" + year;
            buttonDOB.setText(date1);
        }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onRestart() {
        super.onRestart();
        editTextName.setText(R.string.empty);
        editTextDescription.setText(R.string.empty);
        editTextOccupation.setText(R.string.empty);
        buttonDOB.setText(R.string.buttonDOB);
        java.util.Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, (datePicker, year1, month1, day1) -> {
            year = year1;
            month = month1 + 1;
            day = day1;
            String date1 = month + "/" + day + "/" + year;
            buttonDOB.setText(date1);
        }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void openProfile(View view) {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        String occupation = editTextOccupation.getText().toString();
        int age = Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears();

        if (name.equals("") || description.equals("") || occupation.equals("")) {
            // empty strings are not valid form input show a Toast to the user
            Toast.makeText(getApplicationContext(), getString(R.string.errorEmptyEditText), Toast.LENGTH_LONG).show();
            return;
        }

        if (age < 18) {
            Toast.makeText(getApplicationContext(), getString(R.string.errorAge), Toast.LENGTH_LONG).show();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString(Constants.NAME_KEY, name);
        bundle.putString(Constants.DESCRIPTION_KEY, description);
        bundle.putString(Constants.OCCUPATION_KEY, occupation);
        bundle.putInt(Constants.AGE_KEY, age);

        FragmentProfile fragmentProfile = new FragmentProfile();
        fragmentProfile.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentProfile, fragmentProfile, "fragmentProfile");
        transaction.addToBackStack("openFragmentProfile");
        transaction.commit();
        Log.i(TAG, "onCreate()");
    }

    public void openMatches(View view) {
        FragmentSettings fragmentMatches = new FragmentSettings();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentProfile, fragmentMatches, "fragmentMatches");
        transaction.commit();
        Log.i(TAG, "onCreate()");
    }

    public void openSettings(View view) {
        FragmentSettings fragmentSettings = new FragmentSettings();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentProfile, fragmentSettings, "fragmentSettings");
        transaction.commit();
        Log.i(TAG, "onCreate()");
    }

    @Override
    public void onBackStackChanged() {

    }

//    public void onSubmit(View view) {
//        String name = editTextName.getText().toString();
//        String description = editTextDescription.getText().toString();
//        String occupation = editTextOccupation.getText().toString();
//        int age = Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears();
//
//        if (name.equals("") || description.equals("") || occupation.equals("")) {
//            // empty strings are not valid form input show a Toast to the user
//            Toast.makeText(getApplicationContext(), getString(R.string.errorEmptyEditText), Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        if (age < 18) {
//            Toast.makeText(getApplicationContext(), getString(R.string.errorAge), Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        Intent intent = new Intent(this, ProfileActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.NAME_KEY, name);
//        bundle.putString(Constants.DESCRIPTION_KEY, description);
//        bundle.putString(Constants.OCCUPATION_KEY, occupation);
//        bundle.putInt(Constants.AGE_KEY, age);
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }
}