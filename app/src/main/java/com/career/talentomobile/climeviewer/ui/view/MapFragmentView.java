package com.career.talentomobile.climeviewer.ui.view;

import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.google.android.gms.common.api.Status;

/**
 * Created by malkomich on 03/10/2016.
 */

public interface MapFragmentView {

    boolean checkLocationPermission();

    void showLocationDialog(Status status);

    void buildGoogleApiClient();

    void updateLocation(GeoInfo geoInfo);
}
