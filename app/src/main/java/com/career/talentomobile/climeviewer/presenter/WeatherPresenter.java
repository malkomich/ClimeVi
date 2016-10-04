package com.career.talentomobile.climeviewer.presenter;

import android.util.Log;

import com.career.talentomobile.climeviewer.tasks.GetClimeTask;
import com.career.talentomobile.climeviewer.callback.OnWeatherInfoListener;
import com.career.talentomobile.climeviewer.model.GeoPoints;
import com.career.talentomobile.climeviewer.model.WeatherInfo;
import com.career.talentomobile.climeviewer.ui.view.WeatherView;

/**
 * Controller of the weather fragment logic.
 */
public class WeatherPresenter implements OnWeatherInfoListener {

    private final WeatherView view;

    public WeatherPresenter(WeatherView view) {
        this.view = view;
    }

    /**
     * Runs the task to get the weather data for a specific area.
     *
     * @param area
     *             Points which delimited the requested area
     */
    public void updateWeather(GeoPoints area) {
        new GetClimeTask(this).execute(area);
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.callback.OnWeatherInfoListener#onWeatherInfo()
     */
    @Override
    public void onWeatherInfo(WeatherInfo weatherInfo) {
        if(weatherInfo.getTemperature() != null) {
            view.setTemperature(weatherInfo.getTemperature());
        }
    }
}
