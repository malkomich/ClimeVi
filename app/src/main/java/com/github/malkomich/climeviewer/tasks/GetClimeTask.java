package com.github.malkomich.climeviewer.tasks;

import android.os.AsyncTask;

import com.github.malkomich.climeviewer.callback.OnWeatherInfoListener;
import com.github.malkomich.climeviewer.data.rest.WeatherInfoGetter;
import com.github.malkomich.climeviewer.model.GeoPoints;
import com.github.malkomich.climeviewer.model.WeatherInfo;

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
