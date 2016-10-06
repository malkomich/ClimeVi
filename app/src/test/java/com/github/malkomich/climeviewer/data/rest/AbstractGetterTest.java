package com.github.malkomich.climeviewer.data.rest;

import org.json.JSONObject;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

public class AbstractGetterTest {

    private static final String URL_VALID =
        "http://api.geonames.org/searchJSON?q=Valladolid&maxRows=20&startRow=0&lang=en&isNameRequired=true&" +
            "style=FULL&username=ilgeonamessample";
    private static final String URL_WITHOUT_CITY =
        "http://api.geonames.org/searchJSON?q=&maxRows=20&startRow=0&lang=en&isNameRequired=true&" +
            "style=FULL&username=ilgeonamessample";
    private static final String URL_WRONG_CITY =
        "http://api.geonames.org/searchJSON?q=Asdfg&maxRows=20&startRow=0&lang=en&isNameRequired=true&" +
            "style=FULL&username=ilgeonamessample";

    private static final String RESULTS = "totalResultsCount";

    @Test
    public void doRequestValid() throws Exception {

        final JSONObject[] jsonObject = new JSONObject[1];

        AbstractGetter getter = new AbstractGetter() {
            @Override
            public boolean apiSuccess() {
                return false;
            }

            @Override
            protected void init(JSONObject json) {
                jsonObject[0] = json;
            }
        };

        getter.doRequest(new URL(URL_VALID));
        assertTrue(0 < jsonObject[0].optInt(RESULTS));
    }

    @Test
    public void doRequestNoCity() throws Exception {

        final JSONObject[] jsonObject = new JSONObject[1];

        AbstractGetter getter = new AbstractGetter() {
            @Override
            public boolean apiSuccess() {
                return false;
            }

            @Override
            protected void init(JSONObject json) {
                jsonObject[0] = json;
            }
        };
        getter.doRequest(new URL(URL_WITHOUT_CITY));
        assertEquals(0, jsonObject[0].optInt(RESULTS));
    }

    @Test
    public void doRequestWrongCity() throws Exception {

        final JSONObject[] jsonObject = new JSONObject[1];

        AbstractGetter getter = new AbstractGetter() {
            @Override
            public boolean apiSuccess() {
                return false;
            }

            @Override
            protected void init(JSONObject json) {
                jsonObject[0] = json;
            }
        };
        getter.doRequest(new URL(URL_WRONG_CITY));
        assertEquals(0, jsonObject[0].optInt(RESULTS));
    }

}