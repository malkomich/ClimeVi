package com.career.talentomobile.climeviewer.data.rest;

import com.career.talentomobile.climeviewer.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Generic REST client to retrieve data from web services.
 */
public abstract class AbstractGetter {

    private static final String TAG = "GEONAMES SERVICE API";

    /**
     * Check if domain objects has been set.
     *
     * @return Success status
     */
    public abstract boolean apiSuccess();

    /**
     * Calls the REST API to retrieve the String data, and then calls to the
     * parser.
     *
     * @param url
     *            Service URL
     */
    protected void doRequest(URL url) {

        StringBuilder output = new StringBuilder();

        LogUtils.d(TAG, "GET: " + url.toString());

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

        LogUtils.d(TAG, "RESPONSE: " + output.toString());

        parseOutput(output.toString());
    }

    /**
     * Parse a String well JSON-formed to create the domain classes.
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

    /**
     * Initialize data object and save the information to them.
     *
     * @param json
     *            JSON data to parse
     */
    protected abstract void init(JSONObject json);

}
