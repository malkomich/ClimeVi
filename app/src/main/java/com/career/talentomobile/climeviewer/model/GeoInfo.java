package com.career.talentomobile.climeviewer.model;

import android.util.Log;

import com.google.android.gms.location.places.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by malkomich on 03/10/2016.
 */
public class GeoInfo {

    private static final String GEO_STATIONS = "geonames";

    private String placeName;
    private List<GeoStation> stations;
    private Coordinates placeCoords;

    public GeoInfo(JSONObject json, Place place) {
        placeName = place.getName().toString();
        placeCoords = new Coordinates(place.getLatLng().latitude, place.getLatLng().longitude);

        try {
            JSONArray stationJSONArray = json.getJSONArray(GEO_STATIONS);
            stations = new ArrayList<>(stationJSONArray.length());

            for (int i = 0; i < stationJSONArray.length(); i++) {
                JSONObject jsonItem = (JSONObject) stationJSONArray.get(i);

                GeoStation geoStation = new GeoStation(jsonItem);
                if(jsonItem.has(GeoStation.BBOX)) {
                    stations.add(geoStation);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPlaceName() {
        return placeName;
    }

    public Coordinates getPlaceCoordinates() {
        return placeCoords;
    }

    public List<GeoStation> getStations() {
        return stations;
    }
}
