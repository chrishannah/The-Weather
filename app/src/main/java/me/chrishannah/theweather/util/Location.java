package me.chrishannah.theweather.util;

/**
 * Created by christopher on 06/04/2017.
 */

public class Location {
    public Double latitude;
    public Double longitude;

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        String location = "Latitude: " + this.latitude + " Longitude: " + this.longitude;
        return location;
    }
}
