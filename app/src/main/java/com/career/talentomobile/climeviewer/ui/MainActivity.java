package com.career.talentomobile.climeviewer.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.callback.OnPlaceUpdatedListener;
import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.ui.fragment.HistoryFragment;
import com.career.talentomobile.climeviewer.ui.fragment.MapFragment;
import com.career.talentomobile.climeviewer.ui.view.HistoryView;

/**
 * Created by malkomich on 01/10/2016.
 */
public class MainActivity extends FragmentActivity implements OnPlaceUpdatedListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();

    public static final String HISTORY = "history";
    public static final String HISTORY_LENGTH = "history_length";
    public static final String HISTORY_ITEM = "history_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onPlaceUpdated(GeoInfo geoInfo) {
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.updateLocation(geoInfo);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                HistoryView historyView = (HistoryView) getSupportFragmentManager()
                    .findFragmentById(R.id.history_fragment);
                historyView.show();
                break;
            default:
                break;
        }
    }
}
