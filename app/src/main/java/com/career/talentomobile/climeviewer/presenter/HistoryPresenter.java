package com.career.talentomobile.climeviewer.presenter;

import android.content.Context;
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
 * Created by malkomich on 03/10/2016.
 */

public class HistoryPresenter implements View.OnTouchListener {

    private static final String TAG = HistoryPresenter.class.getName();

    private HistoryView view;

    public HistoryPresenter(HistoryView view) {
        this.view = view;
    }

    public void loadHistory(Context context) {
        List<GeoInfo> historyItems = new ArrayList<>();
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences(MainActivity.HISTORY, 0);
        int size = prefs.getInt(MainActivity.HISTORY_LENGTH, 0);
        for(int i = 0; i < size; i++) {
            String json = prefs.getString(MainActivity.HISTORY_ITEM + "_" + i, null);
            historyItems.add(gson.fromJson(json, GeoInfo.class));
        }

        view.setItems(historyItems);
    }

    public void onItemClicked(int position) {
        GeoInfo geoInfo = view.getGeoInfoFromAdapter(position);
        view.updatePlace(geoInfo);
        view.hide();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.view.hide();
        return true;
    }
}
