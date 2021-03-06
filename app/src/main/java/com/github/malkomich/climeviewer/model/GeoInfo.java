package com.github.malkomich.climeviewer.model;

import android.util.Log;

import com.google.android.gms.location.places.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Data object for the location information of a place.
 */
public class GeoInfo {

    // API naming of the JSON objects
    private static final String GEO_STATIONS = "geonames";

    private String placeName;
    private Coordinates placeCoords;
    private List<GeoStation> stations = new ArrayList<>();

    public GeoInfo(JSONObject json, Place place) {
        placeName = place.getName().toString();
        placeCoords = new Coordinates(place.getLatLng().latitude, place.getLatLng().longitude);

        try {
            JSONArray stationJSONArray = json.getJSONArray(GEO_STATIONS);
            stations = new ArrayList<>(stationJSONArray.length());

            for (int i = 0; i < stationJSONArray.length(); i++) {
                JSONObject jsonItem = (JSONObject) stationJSONArray.get(i);

                GeoStation geoStation = new GeoStation(jsonItem);
                Log.d("GeoInfo", "placeCoords: " + place.getLatLng().latitude + place.getLatLng().longitude);
                Log.d("GeoInfo", "stationCoords: " + geoStation.getCoordinates().getLatitude() + ", " + geoStation
                    .getCoordinates().getLongitude());
                if(geoStation.isValid() && geoStation.getCoordinates().isNear(placeCoords)) {
                    stations.add(geoStation);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public GeoInfo() {}

    /**
     * Gets the name of the place, which is retrieved by Google Places API.
     *
     * @return PlaceName
     */
    public String getPlaceName() {
        return placeName;
    }

    /**
     * Gets the coordinates of the location of the place.
     *
     * @return PlaceCoords
     */
    public Coordinates getPlaceCoordinates() {
        return placeCoords;
    }

    /**
     * Gets the weather stations belonging this place.
     *
     * @return Stations
     */
    public List<GeoStation> getStations() {
        return stations;
    }

    public void addStation(GeoStation station) {
        this.stations.add(station);
    }

    /**
     * Get the limit points of the area which contains all the stations.
     *
     * @return GeoPoints
     */
    public GeoPoints getAreaPoints() {
        double north = - Double.MAX_VALUE;
        double south = Double.MAX_VALUE;
        double east = - Double.MAX_VALUE;
        double west = Double.MAX_VALUE;

        for(GeoStation geoStation: stations) {
            GeoPoints stationPoints = geoStation.getGeoPoints();
            if(stationPoints != null) {
                north = (stationPoints.getNorth() > north) ? stationPoints.getNorth() : north;
                south = (stationPoints.getSouth() < south) ? stationPoints.getSouth() : south;
                east = (stationPoints.getEast() > east) ? stationPoints.getEast() : east;
                west = (stationPoints.getWest() < west) ? stationPoints.getWest() : west;
            } else {
                Coordinates stationsCoords = geoStation.getCoordinates();
                north = (stationsCoords.getLatitude() > north) ? stationsCoords.getLatitude() : north;
                south = (stationsCoords.getLatitude() < south) ? stationsCoords.getLatitude() : south;
                east = (stationsCoords.getLongitude() > east) ? stationsCoords.getLongitude() : east;
                west = (stationsCoords.getLongitude() < west) ? stationsCoords.getLongitude() : west;
            }
        }
        return new GeoPoints(north, south, east, west);
    }

    /**
     * Check if the area delimited by this place contains any weather station.
     *
     * @return boolean
     */
    public boolean hasStations() {
        return !stations.isEmpty();
    }
}
