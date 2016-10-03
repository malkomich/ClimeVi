package com.career.talentomobile.climeviewer.model;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by malkomich on 03/10/2016.
 */

public class Place {

    private static final String NAME = "name";
    private static final String COORD = "coord";

    private String name;
    private Coordinates coordinates;

    public Place(String name, JSONObject coordJSON) {
        this.name = name;
        coordinates = (coordJSON != null) ? new Coordinates(coordJSON) : null;
    }

    public Place(JSONObject json) {
        name = json.optString(NAME);
        JSONObject coordJSON = json.optJSONObject(COORD);
        coordinates = (coordJSON != null) ? new Coordinates(coordJSON) : null;
    }

    /**
     * Geo Location of a city wich wraps the latitude and longitude values.
     *
     * @author malkomich
     *
     */
    class Coordinates {
        private static final String OWN_LAT = "lat";
        private static final String OWN_LON = "lon";

        private float latitude;
        private float longitude;

        Coordinates(JSONObject json) {
            latitude = new Float(json.optDouble(Coordinates.OWN_LAT));
            longitude = new Float(json.optDouble(Coordinates.OWN_LON));
        }

        /**
         * Gets the latitude of the geo location
         *
         * @return Latitude
         */
        public float getLatitude() {
            return latitude;
        }

        /**
         * Gets the longitude of the geo location
         *
         * @return Longitude
         */
        public float getLongitude() {
            return longitude;
        }
    }

    /**
     * Gets the city name
     *
     * @return City name
     */
    public String getName() {
        return name;
    }
}
