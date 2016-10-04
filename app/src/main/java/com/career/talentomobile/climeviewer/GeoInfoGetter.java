package com.career.talentomobile.climeviewer;

import android.location.Geocoder;

import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.google.android.gms.location.places.Place;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by malkomich on 03/10/2016.
 */
public class GeoInfoGetter {

    private static final String BASE_URL ="http://api.geonames.org/searchJSON?maxRows=20&startRow=0&lang=en&" +
        "isNameRequired=true&style=FULL&username=ilgeonamessample";

    private GeoInfo geoInfo;

    public GeoInfoGetter(Place place) {
        URL url = buildURL(place.getName().toString());
        if(url != null) {
            doRequest(url, place);
        }
    }

    protected URL buildURL(String place) {
        String path = BASE_URL + "&q=" + place;
        try {
            return new URL(path);
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
    private void doRequest(URL url, Place place) {

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

        parseOutput(output.toString(), place);
    }

    /**
     * Parse a String well JSON-formed to create the domain classes of Weather
     * and Town.
     *
     * @param output
     *            Service Data result
     */
    private void parseOutput(String output, Place place) {

        JSONObject json = null;
        try {
            json = new JSONObject(output);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(json != null) {
            init(json, place);
        }
    }

    protected void init(JSONObject json, Place place) {
        geoInfo = new GeoInfo(json, place);
    }

    public GeoInfo getGeoInfo() {
        return geoInfo;
    }

    public boolean apiSuccess() {
        return (geoInfo != null) ? true : false;
    }
}
