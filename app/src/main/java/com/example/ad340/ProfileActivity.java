package com.example.ad340;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        Bundle b = getIntent().getExtras();
        TextView textViewName = findViewById(R.id.textViewName);
        textViewName.setText(b.getString(Constants.NAME_KEY));
        TextView textViewAge = findViewById(R.id.textViewAge);
        textViewAge.setText(String.valueOf(b.getInt(Constants.AGE_KEY)));
        TextView textViewOccupation = findViewById(R.id.textViewOccupation);
        textViewOccupation.setText(b.getString(Constants.OCCUPATION_KEY));
        TextView textViewDescription = findViewById(R.id.textViewDescription);
        textViewDescription.setText(b.getString(Constants.DESCRIPTION_KEY));
    }

    public void onBack(View view) {
        this.finish();
    }
}
