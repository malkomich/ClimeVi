package com.career.talentomobile.climeviewer.ui.view;

import com.career.talentomobile.climeviewer.model.GeoInfo;

import java.util.List;

/**
 * View logic definition for the history section.
 */
public interface HistoryView {

    void setItems(List<GeoInfo> items);

    GeoInfo getGeoInfoFromAdapter(int position);

    void updatePlace(GeoInfo geoInfo);

    /**
     * Show the history view.
     */
    void show();

    /**
     * Hide the history view.
     */
    void hide();
}
