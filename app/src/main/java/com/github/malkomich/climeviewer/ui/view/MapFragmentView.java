package com.github.malkomich.climeviewer.ui.view;

import com.github.malkomich.climeviewer.model.GeoInfo;
import com.google.android.gms.common.api.Status;

/**
 * View logic definition for the map section.
 */
public interface MapFragmentView {

    /**
     * Check if the location permission is enabled.
     *
     * @return boolean
     */
    boolean checkLocationPermission();

    /**
     * Show a dialog to request enabling the location service.
     *
     * @param status Status
     */
    void showLocationDialog(Status status);

    /**
     * Builds the API client to consume the Google services.
     */
    void buildGoogleApiClient();

    /**
     * Update the location in the view, thanks to the given geolocation data.
     *
     * @param geoInfo
     *                Geolocation data of the place
     */
    void updateLocation(GeoInfo geoInfo);
}
