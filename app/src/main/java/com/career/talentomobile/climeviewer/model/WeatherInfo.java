package com.career.talentomobile.climeviewer.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by malkomich on 04/10/2016.
 */
public class WeatherInfo {

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

    public Double getTemperature() {
        return temp;
    }
}
