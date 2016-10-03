package com.career.talentomobile.climeviewer.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.career.talentomobile.climeviewer.FindPlaceTask;
import com.career.talentomobile.climeviewer.callback.OnGeoLocationInfo;
import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.ui.MainActivity;
import com.career.talentomobile.climeviewer.ui.view.PlaceSearchView;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.gson.Gson;

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
        new FindPlaceTask(this).execute(place);
    }

    @Override
    public void onError(Status status) {
        Log.i(TAG, "An error occurred: " + status);
    }

    @Override
    public void onGeoLocationInfo(GeoInfo geoInfo) {
        view.updatePlace(geoInfo);
    }

    /**
     * Save place request to the history
     *
     * @param geoInfo
     * @param mContext
     * @return
     */
    public boolean saveRequest(GeoInfo geoInfo, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences(MainActivity.HISTORY, 0);
        int length = prefs.getInt(MainActivity.HISTORY_LENGTH, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(MainActivity.HISTORY_LENGTH, length + 1);
        Gson gson = new Gson();
        String json = gson.toJson(geoInfo);
        editor.putString(MainActivity.HISTORY_ITEM + "_" + length, json);
        return editor.commit();
    }
}
