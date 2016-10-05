package com.career.talentomobile.climeviewer.data.rest;

import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.google.android.gms.location.places.Place;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * REST client which gets the required data to locate a place.
 */
public class GeoInfoGetter extends AbstractGetter {

    private static final String TAG = GeoInfoGetter.class.getName();
    private static final String BASE_URL ="http://api.geonames.org/searchJSON?maxRows=20&startRow=0&lang=en&" +
        "isNameRequired=true&style=FULL&username=ilgeonamessample";

    private GeoInfo geoInfo;
    private Place place;

    public GeoInfoGetter(Place place) {
        this.place = place;
        URL url = buildURL(place.getName().toString());
        if(url != null) {
            doRequest(url);
        }
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.data.rest.AbstractGetter#apiSuccess()
     */
    @Override
    public boolean apiSuccess() {
        return geoInfo != null;
    }

    public GeoInfo getGeoInfo() {
        return geoInfo;
    }

    /**
     * Joins the base url of the web service with the required params.
     *
     * @param place
     *              Name of the place to locate
     * @return Service URL
     */
    private URL buildURL(String place) {
        try {
            String path = BASE_URL + "&q=" + URLEncoder.encode(place,"UTF-8");
            return new URL(path);

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.data.rest.AbstractGetter#init()
     */
    @Override
    protected void init(JSONObject json) {
        geoInfo = new GeoInfo(json, place);
    }
}
