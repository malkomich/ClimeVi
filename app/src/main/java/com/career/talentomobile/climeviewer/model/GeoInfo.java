package com.career.talentomobile.climeviewer.model;

import org.json.JSONObject;

/**
 * Created by malkomich on 03/10/2016.
 */

public class GeoInfo {

    private static final String PLACE = "coord";

    private Place place;

    public GeoInfo(JSONObject json) {
        JSONObject cityJSON = json.optJSONObject(PLACE);
        place = new Place(cityJSON);
    }
}
