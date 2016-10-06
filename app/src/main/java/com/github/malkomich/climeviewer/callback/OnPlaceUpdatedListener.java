package com.github.malkomich.climeviewer.callback;

import android.content.SharedPreferences;

import com.github.malkomich.climeviewer.model.GeoInfo;

/**
 * Listener for API's communication when the place is updated.
 */
public interface OnPlaceUpdatedListener {

    /**
     * Triggered when the Map gets updated with a new location
     *
     * @param geoInfo Geolocation data
     */
    void onPlaceUpdated(GeoInfo geoInfo);

    /**
     * Retrieves the places history stored on SharedPreferences.
     *
     * @return SharedPreferences
     */
    SharedPreferences getHistorySharedPreferences();
}
