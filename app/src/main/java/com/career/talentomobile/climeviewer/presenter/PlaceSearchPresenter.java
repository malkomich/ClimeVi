package com.career.talentomobile.climeviewer.presenter;

import android.util.Log;

import com.career.talentomobile.climeviewer.LocationsGetter;
import com.career.talentomobile.climeviewer.callback.OnGeoLocationInfo;
import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.ui.view.PlaceSearchView;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

/**
 * Created by malkomich on 03/10/2016.
 */
public class PlaceSearchPresenter implements PlaceSelectionListener, OnGeoLocationInfo {

    private static final String TAG = PlaceSearchPresenter.class.getName();

    private PlaceSearchView view;

    public PlaceSearchPresenter(PlaceSearchView view) {
        this.view = view;
    }

    @Override
    public void onPlaceSelected(Place place) {
        Log.i(TAG, "Place: " + place.getName());
        new LocationsGetter(this).execute(place);
    }

    @Override
    public void onError(Status status) {
        Log.i(TAG, "An error occurred: " + status);
    }

    @Override
    public void onGeoLocationInfo(GeoInfo geoInfo) {
        view.updatePlace(geoInfo);
    }
}
