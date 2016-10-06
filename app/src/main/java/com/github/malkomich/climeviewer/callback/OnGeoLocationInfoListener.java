package com.github.malkomich.climeviewer.callback;

import com.github.malkomich.climeviewer.model.GeoInfo;

/**
 * Listener for the successful finish of the task to get the Geolocation data for a specific place.
 */
public interface OnGeoLocationInfoListener {

    /**
     * Triggered when the geolocation is retrieved from the API.
     *
     * @param geoInfo Geolocation data
     */
    void onGeoLocationInfo(GeoInfo geoInfo);

}
