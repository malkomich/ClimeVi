package com.career.talentomobile.climeviewer.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.callback.OnPlaceUpdatedListener;
import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.ui.fragment.MapFragment;

/**
 * Created by malkomich on 01/10/2016.
 */
public class MainActivity extends FragmentActivity implements OnPlaceUpdatedListener {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

    }

    @Override
    public void onPlaceUpdated(GeoInfo geoInfo) {
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.updateLocation(geoInfo);
    }
}
