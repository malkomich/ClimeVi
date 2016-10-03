package com.career.talentomobile.climeviewer.model;

import org.json.JSONObject;

/**
 * Created by malkomich on 03/10/2016.
 */
public class GeoStation {

    private static final String BBOX = "bbox";
    private static final String LAT = "lat";
    private static final String LON = "lng";

    private GeoPoints geoPoints;
    private double latitude;
    private double longitude;

    public GeoStation(JSONObject json) {

        if (json.has(BBOX)) {
            geoPoints = new GeoPoints(json.optJSONObject(BBOX));
        }
        if (json.has(LAT)) {
            latitude = json.optDouble(LAT);
        }
        if (json.has(LON)) {
            longitude = json.optDouble(LON);
        }
    }

    /**
     * Gets the geo points of the station
     *
     * @return geoPoints
     */
    public GeoPoints getGeoPoints() {
        return geoPoints;
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

    /**
     * Points of a station's geolocation which indicates north, south, east and west position values.
     *
     * @author malkomich
     *
     */
    class GeoPoints {
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
}
