package com.github.malkomich.climeviewer.callback;

import com.github.malkomich.climeviewer.model.WeatherInfo;

/**
 * Listener for the successful finish of the task to get the Weather data for a specific place.
 */
public interface OnWeatherInfoListener {

    /**
     * Triggered when the weather data is retrieved from the API.
     *
     * @param weatherInfo WeatherInfo
     */
    void onWeatherInfo(WeatherInfo weatherInfo);
}
