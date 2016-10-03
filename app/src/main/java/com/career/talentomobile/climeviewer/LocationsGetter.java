package com.career.talentomobile.climeviewer;

import android.os.AsyncTask;
import android.util.Log;

import com.career.talentomobile.climeviewer.callback.OnGeoLocationInfo;
import com.career.talentomobile.climeviewer.model.GeoInfo;

/**
 * Created by malkomich on 02/10/2016.
 */
public class LocationsGetter extends AsyncTask<String, Void, GeoInfo> {

    private static final String TAG = LocationsGetter.class.getName();

    private OnGeoLocationInfo listener;

    public LocationsGetter(OnGeoLocationInfo listener) {
        super();
        this.listener = listener;
    }

    @Override
    protected GeoInfo doInBackground(String... params) {
        String city = params[0];
        GeoInfoGetter geoInfoGetter = new GeoInfoGetter(city);
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
