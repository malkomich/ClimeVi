package com.career.talentomobile.climeviewer.model;

import org.json.JSONObject;

/**
 * Points of a station's geolocation which indicates north, south, east and west position values.
 *
 * @author malkomich
 *
 */
public class GeoPoints {
    private static final String NORTH = "north";
    private static final String SOUTH = "south";
    private static final String EAST = "east";
    private static final String WEST = "west";

    private double northPoint;
    private double southPoint;
    private double eastPoint;
    private double westPoint;

    GeoPoints(JSONObject json) {
        northPoint = json.optDouble(NORTH);
        southPoint = json.optDouble(SOUTH);
        eastPoint = json.optDouble(EAST);
        westPoint = json.optDouble(WEST);
    }

    GeoPoints(double northPoint, double southPoint, double eastPoint, double westPoint) {
        this.northPoint = northPoint;
        this.southPoint = southPoint;
        this.eastPoint = eastPoint;
        this.westPoint = westPoint;
    }

    /**
     * Gets the north point location
     *
     * @return northPoint
     */
    public double getNorth() {
        return northPoint;
    }

    /**
     * Gets the south point location
     *
     * @return southPoint
     */
    public double getSouth() {
        return southPoint;
    }

    /**
     * Gets the east point location
     *
     * @return eastPoint
     */
    public double getEast() {
        return eastPoint;
    }

    /**
     * Gets the west point location
     *
     * @return westPoint
     */
    public double getWest() {
        return westPoint;
    }
}
