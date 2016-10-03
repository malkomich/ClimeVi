package com.career.talentomobile.climeviewer;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by malkomich on 02/10/2016.
 */

public class LocationsGetter extends AsyncTask<String, Void, String> {

    private static final String TAG = LocationsGetter.class.getName();

    @Override
    protected String doInBackground(String... params) {
        try {
            String city = params[0];
            final String url = "http://api.geonames.org/searchJSON?q=" + city +
                "&maxRows=20&startRow=0&lang=en&isNameRequired=true&style=FULL&username=ilgeonamessample";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            String response = restTemplate.getForObject(url, String.class);
            return response;
        } catch (Exception e) {
            Log.e(TAG + ":doInBackground", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        Log.d(TAG + ":onPostExecute", "RESPONSE: " + response);

    }
}
