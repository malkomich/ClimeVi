package com.career.talentomobile.climeviewer.callback;

import com.career.talentomobile.climeviewer.model.GeoInfo;

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
