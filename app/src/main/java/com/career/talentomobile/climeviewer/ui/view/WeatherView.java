package com.career.talentomobile.climeviewer.ui.view;

import com.career.talentomobile.climeviewer.model.GeoPoints;

/**
 * View logic definition for the weather section.
 */
public interface WeatherView {

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
}
