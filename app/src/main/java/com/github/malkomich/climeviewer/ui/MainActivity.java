package com.github.malkomich.climeviewer.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.malkomich.climeviewer.R;
import com.github.malkomich.climeviewer.callback.OnPlaceUpdatedListener;
import com.github.malkomich.climeviewer.model.GeoInfo;
import com.github.malkomich.climeviewer.ui.view.HistoryView;
import com.github.malkomich.climeviewer.ui.view.MapFragmentView;
import com.github.malkomich.climeviewer.ui.view.WeatherView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Main activity, composed with fragments, which launch the application functionality.
 */
public class MainActivity extends FragmentActivity implements OnPlaceUpdatedListener, View.OnClickListener,
    View.OnLongClickListener {

    private static final String HISTORY = "history";
    public static final String HISTORY_LENGTH = "history_length";
    public static final String HISTORY_ITEM = "history_item";

    private AdView mAdView;

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.callback.OnPlaceUpdatedListener#onPlaceUpdated()
     */
    @Override
    public void onPlaceUpdated(GeoInfo geoInfo) {
        // Center on new location and show station marks
        MapFragmentView mapFragmentView = (MapFragmentView) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragmentView.updateLocation(geoInfo);

        if(geoInfo.hasStations()) {
            // Show weather info fragment
            WeatherView weatherView = (WeatherView) getSupportFragmentManager().findFragmentById(R.id.temp_fragment);
            weatherView.updateWeather(geoInfo.getAreaPoints());
        } else {
            Toast.makeText(this, R.string.no_stations_data, Toast.LENGTH_SHORT).show();
        }
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.callback.OnPlaceUpdatedListener#getHistorySharedPreferences()
     */
    @Override
    public SharedPreferences getHistorySharedPreferences() {
        return getSharedPreferences(HISTORY, 0);
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick()
     */
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

    /* (non-Javadoc)
     * @see android.view.View.OnLongClickListener #onLongClick()
     */
    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Toast.makeText(this, R.string.history_button, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeAds();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        fab.setOnLongClickListener(this);
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onPause()
     */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onResume()
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onDestroy()
     */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    /**
     * Initialize banners with the AdMob app ID, and load them.
     */
    private void initializeAds() {

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        mAdView = (AdView) findViewById(R.id.ad_view);

        AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
    }
}
