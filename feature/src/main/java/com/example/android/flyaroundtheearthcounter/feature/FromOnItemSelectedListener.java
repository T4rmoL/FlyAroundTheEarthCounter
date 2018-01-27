package com.example.android.flyaroundtheearthcounter.feature;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by tats on 27.01.2018.
 */

public class FromOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private MainActivity mActivity;
    public FromOnItemSelectedListener(MainActivity activity) {
        mActivity = activity;
    }
    public MainActivity getActivity() {
        return mActivity;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Location location = (Location) parent.getAdapter().getItem(pos);
        getActivity().setFrom(location);
        getActivity().calculateTripDistance();
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + location.toString() + " " + Double.toString(location.lat) + " " + Double.toString(location.lng),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}