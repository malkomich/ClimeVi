package com.career.talentomobile.climeviewer.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Weather station model.
 */
public class GeoStation {

    // API naming of the JSON objects
    private static final String BBOX = "bbox";
    private static final String LAT = "lat";
    private static final String LON = "lng";
    private static final String I18N_NAMES = "alternateNames";
    private static final String NAME_NAME = "name";
    private static final String NAME_LANG = "lang";
    private static final String NAME_PREFERRED = "isPreferredName";

    private final GeoPoints geoPoints;
    private final Coordinates coordinates;
    private final Map<String, String> names = new HashMap<>();
    private String preferredName;

    public GeoStation(JSONObject json) {

        Double latitude;
        Double longitude;

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
     * @return boolean
     */
    public boolean isValid() {
        return coordinates != null;
    }

    /**
     * Gets the geo points of the area covered by the station
     *
     * @return geoPoints
     */
    public GeoPoints getGeoPoints() {
        return geoPoints;
    }

    /**
     * Gets the exact coordinates of the station
     *
     * @return coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Gets the name of the station in a given language.
     *
     * @return String
     */
    public String getName(String lang) {
        return names.containsKey(lang) ? names.get(lang) : preferredName;
    }
}
