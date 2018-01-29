package com.example.android.flyaroundtheearthcounter.feature;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int totalKm = 0;
    int aroundWorld = 0;
    int tripDistance;
    double currentProgress = 0.0;

    private static final int earthCircumference = 40075;

    private Spinner spinnerFrom, spinnerTo;
    private Button btnSubmit;

    private Location locationFrom, locationTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        btnSubmit = findViewById(R.id.btnSubmit);
        LocationFromAdapter locationFromAdapter = new LocationFromAdapter(this);
        spinnerFrom.setAdapter(locationFromAdapter);
        LocationToAdapter locationToAdapter = new LocationToAdapter(this);
        spinnerTo.setAdapter(locationToAdapter);
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
        calculateAroundWorld();
        calculateProgress();
    }



    /**
     * Displays total Km app user has flown.
     */
    public void displayTotalKmFlyed(int kms) {
        TextView scoreView = findViewById(R.id.totalKmFlyed);
        scoreView.setText(String.valueOf(kms));
    }


    // get the selected dropdown list value
    public void addListenerOnButton() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                totalKm = totalKm + tripDistance;
                displayTotalKmFlyed(totalKm);
                calculateAroundWorld();
                calculateProgress();
                spinnerFrom.setSelection(0);
                spinnerTo.setSelection(0);
            }

        });

    }

    public void addListenerOnSpinnerItemSelection() {
        spinnerFrom.setOnItemSelectedListener(new FromOnItemSelectedListener(this));
        spinnerTo.setOnItemSelectedListener(new ToOnItemSelectedListener(this));
    }

    public void setFrom(Location loc) {
        locationFrom = loc;
    }

    public void setTo(Location loc) {
        locationTo = loc;
    }

    @SuppressLint("DefaultLocale")
    public void calculateTripDistance() {
        if (locationFrom == null || locationTo == null) {
            return;
        }
        tripDistance = (int) Math.round(Location.distance(locationFrom, locationTo));
        TextView txtTripDistance = findViewById(R.id.tripDistance);
        if (tripDistance > 0) {
            txtTripDistance.setText(String.format ("%d", tripDistance));
            txtTripDistance.setVisibility(View.VISIBLE);
        }
        else {
            txtTripDistance.setVisibility(View.INVISIBLE);
        }


    }
    @SuppressLint("DefaultLocale")
    public void calculateAroundWorld() {
        aroundWorld = totalKm / earthCircumference;
        TextView txtTripDistance = findViewById(R.id.totalTripsAroundEarth);
        txtTripDistance.setText(String.format ("%d", aroundWorld));
    }

    public void calculateProgress() {
        int modEarth = totalKm % earthCircumference;
        currentProgress = (double) modEarth / (double) earthCircumference * 100;
        ProgressBar progressBar = findViewById(R.id.sProgressBar);
        progressBar.setProgress((int) currentProgress);
    }
}

