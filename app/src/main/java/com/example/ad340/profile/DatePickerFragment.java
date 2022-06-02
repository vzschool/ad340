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
    private final String TAG = MainActivity.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), DatePickerFragment.this, year, month, day);
    }

    public void onDateSet(DatePicker view, int yyyy, int mm, int dd) {
        Bundle bundle = new Bundle();
        bundle.putString("DOB", yyyy + "/" + (mm+1) + "/" + dd);
        bundle.putInt("yyyy", yyyy);
        bundle.putInt("mm", mm);
        bundle.putInt("dd", dd);
        getParentFragmentManager().setFragmentResult("requestKey", bundle);
    }
}