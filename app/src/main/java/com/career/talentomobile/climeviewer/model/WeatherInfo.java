package com.career.talentomobile.climeviewer.model;

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
    private static final String HUMIDITY = "humidity";

    private Double temp = 0.0;
    private Double humidity = 0.0;

    public WeatherInfo(JSONObject json) {
        try {
            JSONArray stationJSONArray = json.getJSONArray(OBSERVATIONS);
            int i;
            for (i = 0; i < stationJSONArray.length(); i++) {
                JSONObject jsonItem = (JSONObject) stationJSONArray.get(i);

                temp += jsonItem.optDouble(TEMPERATURE);
                humidity += jsonItem.optDouble(HUMIDITY);
            }

            temp = (temp == 0) ? null : temp / i;
            humidity = (humidity == 0) ? null : humidity / i;

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

    /**
     * Gets the average of the current humidity.
     *
     * @return humidity
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     * Check if the weather data has been successfully set.
     *
     * @return boolean
     */
    public boolean isValid() {
        return temp != null && humidity != null;
    }
}
