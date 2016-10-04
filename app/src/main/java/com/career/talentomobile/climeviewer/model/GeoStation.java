package com.career.talentomobile.climeviewer.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by malkomich on 03/10/2016.
 */
public class GeoStation {

    public static final String BBOX = "bbox";
    public static final String LAT = "lat";
    public static final String LON = "lng";

    private static final String I18N_NAMES = "alternateNames";
    private static final String NAME_NAME = "name";
    private static final String NAME_LANG = "lang";
    private static final String NAME_PREFERRED = "isPreferredName";

    private GeoPoints geoPoints;
    private Coordinates coordinates;
    private Map<String, String> names = new HashMap<>();
    private String preferredName;

    public GeoStation(JSONObject json) {

        Double latitude = null;
        Double longitude = null;

        geoPoints = (json.has(BBOX)) ? new GeoPoints(json.optJSONObject(BBOX)) : null;
        latitude = json.optDouble(LAT);
        longitude = json.optDouble(LON);
        coordinates = (latitude != null && longitude != null) ? new Coordinates(latitude, longitude) : null;

        if(json.has(I18N_NAMES)) {
            JSONArray namesJSONArray = json.optJSONArray(I18N_NAMES);
            try {
                for (int i = 0; i < namesJSONArray.length(); i++) {
                    JSONObject jsonName = (JSONObject) namesJSONArray.get(i);

                    String name = jsonName.optString(NAME_NAME);
                    boolean preferred = jsonName.optBoolean(NAME_PREFERRED, false);
                    names.put(jsonName.optString(NAME_LANG), name);
                    if (preferred) {
                        preferredName = name;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if the geo station has the required field values.
     *
     * @return
     */
    public boolean isValid() {
        return (geoPoints != null && coordinates != null);
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
     * Gets the coordinates of the station
     *
     * @return coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Gets the name of the station in a given language.
     */
    public String getName(String lang) {
        return names.containsKey(lang) ? names.get(lang) : preferredName;
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
