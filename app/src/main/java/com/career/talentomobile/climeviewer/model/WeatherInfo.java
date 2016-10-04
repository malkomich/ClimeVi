package com.career.talentomobile.climeviewer.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Data object for the weather information in a specific place.
 */
public class WeatherInfo {

    // API naming of the JSON objects
    private static final String OBSERVATIONS = "weatherObservations";
    private static final String TEMPERATURE = "temperature";

    private double temp;

    public WeatherInfo(JSONObject json) {
        try {
            JSONArray stationJSONArray = json.getJSONArray(OBSERVATIONS);
            int i;
            for (i = 0; i < stationJSONArray.length(); i++) {
                JSONObject jsonItem = (JSONObject) stationJSONArray.get(i);

                temp += jsonItem.optDouble(TEMPERATURE);
            }
            temp /= i;
            Log.d("WeatherInfo", "Temperature: " + temp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the average of the current temperature.
     *
     * @return temp
     */
    public Double getTemperature() {
        return temp;
    }
}
