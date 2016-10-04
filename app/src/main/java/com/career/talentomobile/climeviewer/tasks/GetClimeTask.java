package com.career.talentomobile.climeviewer.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.career.talentomobile.climeviewer.callback.OnWeatherInfoListener;
import com.career.talentomobile.climeviewer.data.rest.WeatherInfoGetter;
import com.career.talentomobile.climeviewer.model.GeoPoints;
import com.career.talentomobile.climeviewer.model.WeatherInfo;

/**
 * Created by malkomich on 04/10/2016.
 */
public class GetClimeTask extends AsyncTask<GeoPoints, Void, WeatherInfo> {

    private static final String TAG = GetClimeTask.class.getName();

    private OnWeatherInfoListener listener;

    public GetClimeTask(OnWeatherInfoListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    protected WeatherInfo doInBackground(GeoPoints... geoPoints) {
        GeoPoints area = geoPoints[0];
        WeatherInfoGetter weatherInfoGetter = new WeatherInfoGetter(area);
        if(weatherInfoGetter.apiSuccess()) {
            return weatherInfoGetter.getWeatherInfo();
        }
        return null;
    }

    @Override
    protected void onPostExecute(WeatherInfo weatherInfo) {
        Log.d(TAG + ":onPostExecute", "RESPONSE: " + weatherInfo);

        if(weatherInfo != null) {
            listener.onWeatherInfo(weatherInfo);
        }
    }
}
