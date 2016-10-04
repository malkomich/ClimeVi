package com.career.talentomobile.climeviewer.data.rest;

import android.util.Log;

import com.career.talentomobile.climeviewer.model.GeoPoints;
import com.career.talentomobile.climeviewer.model.WeatherInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by malkomich on 04/10/2016.
 */
public class WeatherInfoGetter {

    private static final String TAG = WeatherInfoGetter.class.getName();
    private static final String BASE_URL ="http://api.geonames.org/weatherJSON?username=ilgeonamessample";

    private WeatherInfo weatherInfo;

    public WeatherInfoGetter(GeoPoints area) {
        URL url = buildURL(area);
        if(url != null) {
            doRequest(url);
        }
    }

    public boolean apiSuccess() {
        return weatherInfo != null;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    protected URL buildURL(GeoPoints area) {
        StringBuilder builder = new StringBuilder().append(BASE_URL)
            .append("&north=").append(area.getNorth())
            .append("&south=").append(area.getSouth())
            .append("&east=").append(area.getEast())
            .append("&west=").append(area.getWest());
        Log.d(TAG, "URL: " + builder.toString());
        try {
            return new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Calls the REST API to retrieve the String data, and then calls to the
     * parser.
     *
     * @param url
     *            Service URL
     */
    private void doRequest(URL url) {

        StringBuilder output = new StringBuilder();

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String line;
            while ((line = br.readLine()) != null) {
                output.append(line);
            }

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        }

        parseOutput(output.toString());
    }

    /**
     * Parse a String well JSON-formed to create the domain classes of Weather
     * and Town.
     *
     * @param output
     *            Service Data result
     */
    private void parseOutput(String output) {

        JSONObject json = null;
        try {
            json = new JSONObject(output);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(json != null) {
            init(json);
        }
    }

    private void init(JSONObject json) {
        weatherInfo = new WeatherInfo(json);
    }
}
