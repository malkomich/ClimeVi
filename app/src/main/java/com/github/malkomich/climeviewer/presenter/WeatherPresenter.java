package com.github.malkomich.climeviewer.presenter;

import com.github.malkomich.climeviewer.tasks.GetClimeTask;
import com.github.malkomich.climeviewer.callback.OnWeatherInfoListener;
import com.github.malkomich.climeviewer.model.GeoPoints;
import com.github.malkomich.climeviewer.model.WeatherInfo;
import com.github.malkomich.climeviewer.ui.view.WeatherView;

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
        if(weatherInfo.isValid()) {
            view.setTemperature(weatherInfo.getTemperature());
            view.setHumidity(weatherInfo.getHumidity());
        } else {
            view.noWeatherInfo();
        }
    }
}
