package com.github.malkomich.climeviewer.callback;

import android.os.Bundle;

/**
 * Handler of log messages to monitor the application usage.
 */
public interface Logger {

    /**
     * Logs a custom event performed on the app.
     *
     * @param name
     *             Event name
     * @param params
     *               Collection of data to report
     */
    void logEvent(String name, Bundle params);

    /**
     * Logs a place search event.
     *
     * @param dataType
     *                Indicates the type of data requested to the API. For instance <strong>weather</strong> to get
     *                data like temperature or humidity of the place, or <strong>geolocation</strong> to get data
     *                like coordinates, country or population
     * @param location
     *                 Name of place requested
     */
    void logSearch(String dataType, String location);
}
