package com.career.talentomobile.climeviewer.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malkomich on 03/10/2016.
 */

public class GeoInfo {

    private static final String GEO_STATIONS = "geonames";

    private String name;
    private List<GeoStation> stations;

    public GeoInfo(JSONObject json, String place) {
        name = place;
        try {
            JSONArray stationJSONArray = json.getJSONArray(GEO_STATIONS);
            stations = new ArrayList<>(stationJSONArray.length());

            for (int i = 0; i < stationJSONArray.length(); i++) {
                JSONObject jsonItem = (JSONObject) stationJSONArray.get(i);

                GeoStation geoStation = new GeoStation(jsonItem);
                stations.add(geoStation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public List<GeoStation> getStations() {
        return stations;
    }
}
