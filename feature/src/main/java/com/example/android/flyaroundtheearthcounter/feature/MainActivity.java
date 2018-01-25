package com.example.android.flyaroundtheearthcounter.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int totalKm = 0;
    int aroundWorld = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    /**
     * Displays total Km app user has flyed.
     */
    public void displayTotalKmFlyed(int score) {
        TextView scoreView = (TextView) findViewById(R.id.totalKmFlyed);
        scoreView.setText(String.valueOf(score));
    }

    public void calculateDistance(View view) {
        totalKm = totalKm + 100;
        displayTotalKmFlyed(totalKm);
    }
}
