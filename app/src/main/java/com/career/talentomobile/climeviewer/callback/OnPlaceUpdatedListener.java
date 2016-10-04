package com.career.talentomobile.climeviewer.callback;

import android.content.SharedPreferences;

import com.career.talentomobile.climeviewer.model.GeoInfo;

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
