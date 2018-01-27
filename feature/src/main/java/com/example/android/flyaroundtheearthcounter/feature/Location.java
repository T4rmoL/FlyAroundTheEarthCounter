package com.example.android.flyaroundtheearthcounter.feature;

/**
 * Created by tats on 27.01.2018.
 */

public class Location {

    public String name;
    public double lat;
    public double lng;
    public Location(String initName, double initLat, double initLng) {
        name = initName;
        lat = initLat;
        lng = initLng;
    }
    public String toString() {
        return name;
    }
    
    public static double distance(Location loc1, Location loc2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(loc2.lat - loc1.lat);
        double lonDistance = Math.toRadians(loc2.lng - loc1.lng);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(loc1.lat)) * Math.cos(Math.toRadians(loc2.lat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
