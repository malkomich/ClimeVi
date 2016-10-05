package com.career.talentomobile.climeviewer.presenter;

import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;

import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.ui.MainActivity;
import com.career.talentomobile.climeviewer.ui.view.HistoryView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller of the history fragment logic.
 */
public class HistoryPresenter implements View.OnTouchListener {

    private final HistoryView view;

    public HistoryPresenter(HistoryView view) {
        this.view = view;
    }

    /**
     * Fill the history listview with the saved places from SharedPreferences.
     *
     * @param prefs
     *              SharedPreferences which contains the places history
     */
    public void loadHistory(SharedPreferences prefs) {
        List<GeoInfo> historyItems = new ArrayList<>();
        Gson gson = new Gson();
        int size = prefs.getInt(MainActivity.HISTORY_LENGTH, 0);
        for(int i = 0; i < size; i++) {
            String json = prefs.getString(MainActivity.HISTORY_ITEM + "_" + i, null);
            historyItems.add(gson.fromJson(json, GeoInfo.class));
        }

        view.setItems(historyItems);
    }

    /**
     * Update the location when a place is chosen from the history listview.
     *
     * @param position
     *                  Index of the chosen item
     */
    public void onItemClicked(int position) {
        GeoInfo geoInfo = view.getGeoInfoFromAdapter(position);
        view.updatePlace(geoInfo);
        view.hide();
    }

    /* (non-Javadoc)
     * @see android.view.View.OnTouchListener#onTouch()
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.view.hide();
        return true;
    }
}
