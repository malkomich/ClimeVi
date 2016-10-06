package com.github.malkomich.climeviewer.tasks;

import android.os.AsyncTask;

import com.github.malkomich.climeviewer.callback.OnGeoLocationInfoListener;
import com.github.malkomich.climeviewer.data.rest.GeoInfoGetter;
import com.github.malkomich.climeviewer.model.GeoInfo;
import com.google.android.gms.location.places.Place;

/**
 * Asynchronous task to retrieve the geolocation data of a place.
 */
public class FindPlaceTask extends AsyncTask<Place, Void, GeoInfo> {

    private final OnGeoLocationInfoListener listener;

    public FindPlaceTask(OnGeoLocationInfoListener listener) {
        super();
        this.listener = listener;
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#doInBackground()
     */
    @Override
    protected GeoInfo doInBackground(Place... params) {

        if(params.length > 0) {
            Place place = params[0];
            GeoInfoGetter geoInfoGetter = new GeoInfoGetter(place);
            if (geoInfoGetter.apiSuccess()) {
                return geoInfoGetter.getGeoInfo();
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#onPostExecute()
     */
    @Override
    protected void onPostExecute(GeoInfo geoInfo) {
        if(geoInfo != null) {
            listener.onGeoLocationInfo(geoInfo);
        }
    }
}
