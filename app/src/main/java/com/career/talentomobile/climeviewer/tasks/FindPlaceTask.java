package com.career.talentomobile.climeviewer.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.career.talentomobile.climeviewer.callback.OnGeoLocationInfoListener;
import com.career.talentomobile.climeviewer.data.rest.GeoInfoGetter;
import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.google.android.gms.location.places.Place;

/**
 * Created by malkomich on 02/10/2016.
 */
public class FindPlaceTask extends AsyncTask<Place, Void, GeoInfo> {

    private static final String TAG = FindPlaceTask.class.getName();

    private final OnGeoLocationInfoListener listener;

    public FindPlaceTask(OnGeoLocationInfoListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    protected GeoInfo doInBackground(Place... params) {
        Place place = params[0];
        GeoInfoGetter geoInfoGetter = new GeoInfoGetter(place);
        if(geoInfoGetter.apiSuccess()) {
            return geoInfoGetter.getGeoInfo();
        }
        return null;
    }

    @Override
    protected void onPostExecute(GeoInfo geoInfo) {
        Log.d(TAG + ":onPostExecute", "RESPONSE: " + geoInfo);

        if(geoInfo != null) {
            listener.onGeoLocationInfo(geoInfo);
        }
    }
}
