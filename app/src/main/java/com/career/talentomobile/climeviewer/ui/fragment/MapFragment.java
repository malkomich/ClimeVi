package com.career.talentomobile.climeviewer.ui.fragment;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.presenter.MapFragmentPresenter;
import com.career.talentomobile.climeviewer.ui.view.MapFragmentView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Fragment implementation of the map section view.
 */
public class MapFragment extends Fragment implements MapFragmentView {

    private static final String TAG = MapFragment.class.getName();

    private MapFragmentPresenter presenter;

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreate()
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MapFragmentPresenter(this);
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView()
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(presenter);

        return view;
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onPause()
     */
    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.MapFragmentView#buildGoogleApiClient()
     */
    @Override
    public synchronized void buildGoogleApiClient() {
        Log.d(TAG, "buildGoogleApiClient");

        GoogleApiClient apiClient = new GoogleApiClient.Builder(getActivity())
            .addConnectionCallbacks(presenter)
            .addOnConnectionFailedListener(presenter)
            .addApi(LocationServices.API)
            .addApi(Places.GEO_DATA_API)
            .addApi(Places.PLACE_DETECTION_API)
            .build();

        presenter.setGoogleApiClient(apiClient);
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.MapFragmentView#checkLocationPermission()
     */
    @Override
    public boolean checkLocationPermission(){
        Log.d(TAG, "checkLocationPermission");

        if (ContextCompat.checkSelfPermission(getActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)) {

                //TODO:
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                //(just doing it here for now, note that with this code, no explanation is shown)
                ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.MapFragmentView#showLocationDialog()
     */
    @Override
    public void showLocationDialog(Status status) {
        try {
            status.startResolutionForResult(getActivity(), 1000);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.MapFragmentView#updateLocation()
     */
    @Override
    public void updateLocation(GeoInfo geoInfo) {
        presenter.updateLocation(geoInfo);
    }
}
