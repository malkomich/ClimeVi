package com.career.talentomobile.climeviewer.presenter;

import android.util.Log;

import com.career.talentomobile.climeviewer.GetClimeTask;
import com.career.talentomobile.climeviewer.callback.OnWeatherInfoListener;
import com.career.talentomobile.climeviewer.model.GeoPoints;
import com.career.talentomobile.climeviewer.model.WeatherInfo;
import com.career.talentomobile.climeviewer.ui.view.TemperatureView;

/**
 * Created by malkomich on 04/10/2016.
 */

public class TemperaturePresenter implements OnWeatherInfoListener {

    private static final String TAG = TemperaturePresenter.class.getName();

    private final TemperatureView view;

    public TemperaturePresenter(TemperatureView view) {
        this.view = view;
    }

    public void updateWeather(GeoPoints area) {
        Log.d(TAG, "updateWeather");
        new GetClimeTask(this).execute(area);
    }

    @Override
    public void onWeatherInfo(WeatherInfo weatherInfo) {
        if(weatherInfo.getTemperature() != null) {
            view.setTemperature(weatherInfo.getTemperature());
        }
    }
}
