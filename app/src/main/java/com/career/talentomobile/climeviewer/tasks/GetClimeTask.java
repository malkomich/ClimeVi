package com.career.talentomobile.climeviewer.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.career.talentomobile.climeviewer.callback.OnWeatherInfoListener;
import com.career.talentomobile.climeviewer.data.rest.WeatherInfoGetter;
import com.career.talentomobile.climeviewer.model.GeoPoints;
import com.career.talentomobile.climeviewer.model.WeatherInfo;

/**
 * Asynchronous task to retrieve the weather data of a place.
 */
public class GetClimeTask extends AsyncTask<GeoPoints, Void, WeatherInfo> {

    private final OnWeatherInfoListener listener;

    public GetClimeTask(OnWeatherInfoListener listener) {
        super();
        this.listener = listener;
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#doInBackground()
     */
    @Override
    protected WeatherInfo doInBackground(GeoPoints... geoPoints) {

        if(geoPoints.length > 0) {
            GeoPoints area = geoPoints[0];
            WeatherInfoGetter weatherInfoGetter = new WeatherInfoGetter(area);
            if (weatherInfoGetter.apiSuccess()) {
                return weatherInfoGetter.getWeatherInfo();
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#onPostExecute()
     */
    @Override
    protected void onPostExecute(WeatherInfo weatherInfo) {
        if(weatherInfo != null) {
            listener.onWeatherInfo(weatherInfo);
        }
    }
}
