package com.career.talentomobile.climeviewer.model;

/**
 * Created by malkomich on 03/10/2016.
 */

public class Coordinates {

    private double latitude;
    private double longitude;

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the latitude of the geo location
     *
     * @return Latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of the geo location
     *
     * @return Longitude
     */
    public double getLongitude() {
        return longitude;
    }
}
