package com.github.malkomich.climeviewer.ui.view;

import com.github.malkomich.climeviewer.model.GeoInfo;

/**
 * View logic definition for the place search section.
 */
public interface PlaceSearchView {

    String DATATYPE = "geolocation";

    void updatePlace(GeoInfo geoInfo);
}
