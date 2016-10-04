package com.career.talentomobile.climeviewer.ui.view;

import com.career.talentomobile.climeviewer.model.GeoPoints;

/**
 * Created by malkomich on 04/10/2016.
 */

public interface TemperatureView {

    void updateWeather(GeoPoints area);

    void setTemperature(double temperature);
}
