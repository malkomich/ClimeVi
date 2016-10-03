package com.career.talentomobile.climeviewer.ui.view;

import com.career.talentomobile.climeviewer.model.GeoInfo;

import java.util.List;

/**
 * Created by malkomich on 03/10/2016.
 */

public interface HistoryView {

    void setItems(List<GeoInfo> items);

    GeoInfo getGeoInfoFromAdapter(int position);

    void updatePlace(GeoInfo geoInfo);

    void show();

    void hide();
}
