package com.example.android.flyaroundtheearthcounter.feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;


/**
 * Created by tats on 27.01.2018.
 */

public abstract class LocationAdapter extends BaseAdapter implements SpinnerAdapter {
    private Location[] locations;

    private final LayoutInflater mInflater;

    public LocationAdapter(Context context) {

        mInflater = LayoutInflater.from(context);
        locations = new Location[]{
                new Location("Tallinn", 59.4161958, 24.7997751),
                new Location("Corfu", 39.6076486, 19.9122921),
                new Location("Melbourne", -37.669008, 144.8388333),
                new Location("Moscow", 55.4103099, 37.9002573),
                new Location("Shanghai", 31.1443485, 121.806079),
                new Location("New York", 40.7681419, -73.8922882),

        };
        
    }



    public LayoutInflater getInflater() {
        return mInflater;
    }

    @Override
    public int getCount() {

        return locations.length;
    }

    @Override
    public Location getItem(int position) {
        return locations[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

