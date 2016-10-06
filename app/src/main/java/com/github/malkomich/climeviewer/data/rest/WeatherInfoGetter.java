package com.github.malkomich.climeviewer.data.rest;

import com.github.malkomich.climeviewer.model.GeoPoints;
import com.github.malkomich.climeviewer.model.WeatherInfo;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * REST client which gets the weather data for a delimited area.
 */
public class WeatherInfoGetter extends AbstractGetter {

    private static final String BASE_URL ="http://api.geonames.org/weatherJSON?username=ilgeonamessample";

    private WeatherInfo weatherInfo;

    public WeatherInfoGetter(GeoPoints area) {
        URL url = buildURL(area);
        if(url != null) {
            doRequest(url);
        }
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.data.rest.AbstractGetter#apiSuccess()
     */
    @Override
    public boolean apiSuccess() {
        return weatherInfo != null;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    /**
     * Joins the base url of the web service with the required params.
     *
     * @param area
     *              Points which delimit the area
     * @return Service URL
     */
    private URL buildURL(GeoPoints area) {
        StringBuilder builder = new StringBuilder().append(BASE_URL)
            .append("&north=").append(area.getNorth())
            .append("&south=").append(area.getSouth())
            .append("&east=").append(area.getEast())
            .append("&west=").append(area.getWest());
        try {
            return new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.data.rest.AbstractGetter#init()
     */
    @Override
    protected void init(JSONObject json) {
        weatherInfo = new WeatherInfo(json);
    }
}
