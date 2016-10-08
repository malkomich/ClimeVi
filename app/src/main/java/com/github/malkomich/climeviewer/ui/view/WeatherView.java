package com.github.malkomich.climeviewer.ui.view;

import com.github.malkomich.climeviewer.model.GeoPoints;

/**
 * View logic definition for the weather section.
 */
public interface WeatherView extends BaseView {

    String DATA_TYPE = "weather";

    /**
     * Update the weather info in the view, thanks to geolocation points of the area.
     *
     * @param area
     *             Location points to delimit the area
     */
    void updateWeather(GeoPoints area);

    /**
     * Update the temperature shown in the view for the area requested.
     *
     * @param temperature
     *                    Temperature in Celsius
     */
    void setTemperature(double temperature);

    /**
     * Update the humidity shown in the view for the area requested.
     *
     * @param humidity
 *                     Humidity percentage
     */
    void setHumidity(double humidity);

    /**
     * Show a view notification when no weather data provided.
     */
    void noWeatherInfo();
}
