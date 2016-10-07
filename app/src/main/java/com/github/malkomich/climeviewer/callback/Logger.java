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
     * @param location
     *                 Name of place requested
     * @param searchLevel
     *                    Indicates the number of requests to the API done by the search. For instance searching from
     *                    history produces only <strong>1</strong> requests (for weather data), and searching from
     *                    autocomplete field produces <strong>2</strong> requests(geolocation & weather data)
     */
    void logSearch(String location, int searchLevel);
}
