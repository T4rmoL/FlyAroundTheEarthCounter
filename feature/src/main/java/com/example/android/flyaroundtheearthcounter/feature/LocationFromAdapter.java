package com.example.android.flyaroundtheearthcounter.feature;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tats on 27.01.2018.
 */

public class LocationFromAdapter extends LocationAdapter {
    public LocationFromAdapter(Context context) {
        super (context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        final TextView text;
        if (convertView == null) {
            convertView = getInflater().inflate(R.layout.custom_spinner_item, container, false);
        }
        text = (TextView) convertView;
        final Location item = getItem(position);
        text.setText(item.toString());
        return convertView;
    }
}