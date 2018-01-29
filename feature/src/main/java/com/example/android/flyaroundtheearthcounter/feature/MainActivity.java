package com.example.android.flyaroundtheearthcounter.feature;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

//    Variable that stores all flights distances
    int totalKm = 0;
//    Variable that stores how many times app user has flown around the world
    int aroundWorld = 0;
//    Variable that calculates current flights distance
    int tripDistance;
//    Variable that stores current progress on progressbar - how far app user is to complete
// his/her around the world trip. Because of Java math it CANT HAVE integral value.
    double currentProgress = 0.0;

    private static final int earthCircumference = 40075;

    private Spinner spinnerFrom, spinnerTo;
    private Button btnSubmit;

    private Location locationFrom, locationTo;
    /**
     * With these three lines below app will be in fullscreen mode:
     *
     *         requestWindowFeature(Window.FEATURE_NO_TITLE);
     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
     WindowManager.LayoutParams.FLAG_FULLSCREEN);
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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


    /**
     * All methods that will trigger when Submit Button is clicked
     */
    public void addListenerOnButton() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//               Calculating total Km app owner has flied
                totalKm = totalKm + tripDistance;
//                Displaying total Km app owner has flied
                displayTotalKmFlyed(totalKm);
//                Calculating how many trips around the Earth app owner has made
                calculateAroundWorld();
//                Calculates Progres Bar progress
                calculateProgress();
//                Sets first spinner to the default state
                spinnerFrom.setSelection(0);
//                Sets second spinner to the default state
                spinnerTo.setSelection(0);
            }

        });

    }

    /**
     * Method that "listens" what choices app user makes with spinners (drop down menues)
     */

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
        TextView txtConfirmation = findViewById(R.id.confirmation);
        if (tripDistance > 0) {
            txtTripDistance.setText(String.format ("%d", tripDistance));
            txtTripDistance.setVisibility(View.VISIBLE);
            txtConfirmation.setVisibility(View.VISIBLE);

//If user has not selected anything then two views will stay INVISIBLE
        }
        else {
            txtTripDistance.setVisibility(View.INVISIBLE);
            txtConfirmation.setVisibility(View.INVISIBLE);
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

