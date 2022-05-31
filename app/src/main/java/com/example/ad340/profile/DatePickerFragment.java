package com.example.ad340.profile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.ad340.MainActivity;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

public class DatePickerFragment extends AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener {

    private String TAG = MainActivity.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), DatePickerFragment.this, year, month, day);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {

//        populateSetDate(yy, mm+1, dd);
    }

//    public void populateSetDate(int year, int month, int day) {
//        dob.setText(month+"/"+day+"/"+year);
//    }

}