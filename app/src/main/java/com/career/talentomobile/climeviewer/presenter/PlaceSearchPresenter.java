package com.career.talentomobile.climeviewer.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.career.talentomobile.climeviewer.tasks.FindPlaceTask;
import com.career.talentomobile.climeviewer.callback.OnGeoLocationInfoListener;
import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.ui.MainActivity;
import com.career.talentomobile.climeviewer.ui.view.PlaceSearchView;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.gson.Gson;

/**
 * Controller of the place's search fragment logic.
 */
public class PlaceSearchPresenter implements PlaceSelectionListener, OnGeoLocationInfoListener {

    private static final String TAG = PlaceSearchPresenter.class.getName();

    private final PlaceSearchView view;

    public PlaceSearchPresenter(PlaceSearchView view) {
        this.view = view;
    }

    @Override
    public void onPlaceSelected(Place place) {
        Log.i(TAG, "onPlaceSelected: " + place.getName());
        new FindPlaceTask(this).execute(place);
    }

    @Override
    public void onError(Status status) {
        Log.i(TAG, "An error occurred: " + status);
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.callback.OnGeoLocationInfoListener#onGeoLocationInfo()
     */
    @Override
    public void onGeoLocationInfo(GeoInfo geoInfo) {
        view.updatePlace(geoInfo);
    }

    /**
     * Save place request to the history
     *
     * @param geoInfo
     *                Geolocation data
     * @param prefs
     *              SharedPreferences which contains the places history
     */
    public void saveRequest(GeoInfo geoInfo, SharedPreferences prefs) {
        Gson gson = new Gson();
        int length = prefs.getInt(MainActivity.HISTORY_LENGTH, 0);

        String prevJson = prefs.getString(MainActivity.HISTORY_ITEM + "_" + (length - 1), null);
        GeoInfo prevEntry = gson.fromJson(prevJson, GeoInfo.class);

        if(prevEntry == null || !prevEntry.getPlaceCoordinates().equals(geoInfo.getPlaceCoordinates())) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(MainActivity.HISTORY_LENGTH, length + 1);
            String json = gson.toJson(geoInfo);
            editor.putString(MainActivity.HISTORY_ITEM + "_" + length, json);
            editor.apply();
        }
    }
}
