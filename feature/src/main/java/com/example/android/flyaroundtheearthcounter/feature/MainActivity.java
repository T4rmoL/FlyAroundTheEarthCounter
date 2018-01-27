package com.example.android.flyaroundtheearthcounter.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int totalKm = 0;
    int aroundWorld = 0;
    int tripDistance;

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
    }



    /**
     * Displays total Km app user has flown.
     */
    public void displayTotalKmFlyed(int score) {
        TextView scoreView = findViewById(R.id.totalKmFlyed);
        scoreView.setText(String.valueOf(score));
    }

    public void calculateDistance(View view) {
        totalKm = totalKm + 100;
        displayTotalKmFlyed(totalKm);
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                totalKm = totalKm + tripDistance;
                displayTotalKmFlyed(totalKm);
                calculateAroundWorld();
                Toast.makeText(MainActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinnerFrom.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(spinnerTo.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
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

    public void calculateTripDistance() {
        if (locationFrom == null || locationTo == null) {
            return;
        }
        tripDistance = (int) Math.round(Location.distance(locationFrom, locationTo));
        TextView txtTripDistance = findViewById(R.id.tripDistance);
        txtTripDistance.setText(String.format ("%d", tripDistance));
    }

    public void calculateAroundWorld() {
        aroundWorld = totalKm / earthCircumference;
        TextView txtTripDistance = findViewById(R.id.totalTripsAroundEarth);
        txtTripDistance.setText(String.format ("%d", aroundWorld));
    }
}

