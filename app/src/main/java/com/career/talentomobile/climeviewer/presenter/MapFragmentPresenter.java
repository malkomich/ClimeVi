package com.career.talentomobile.climeviewer.presenter;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.model.GeoStation;
import com.career.talentomobile.climeviewer.ui.view.MapFragmentView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

/**
 * Created by malkomich on 03/10/2016.
 */

public class MapFragmentPresenter implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener, ResultCallback<LocationSettingsResult>,
    GoogleMap.OnMyLocationButtonClickListener{

    private static final String TAG = MapFragmentPresenter.class.getName();
    private static final float DEFAULT_ZOOM = 9.0f;

    private MapFragmentView view;
    private GoogleMap mMap;
    private GoogleApiClient mApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;

    public MapFragmentPresenter(MapFragmentView view) {
        this.view = view;
    }

    public void onPause() {
        // Stop location updates when Activity is no longer active
        if (mApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mApiClient, this);
        }
    }

    public void initMapsApi() {
        if(view.checkLocationPermission()) {
            if(mApiClient == null) {
                view.buildGoogleApiClient();
            }
            mMap.setMyLocationEnabled(true);
        }
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        mApiClient = googleApiClient;
        mApiClient.connect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady");

        mMap = googleMap;
        initMapsApi();
        mMap.setOnMyLocationButtonClickListener(this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected");

        if (view.checkLocationPermission()) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mApiClient);
        if (mLastLocation != null) {
            double lat = mLastLocation.getLatitude();
            double lng = mLastLocation.getLongitude();

            LatLng loc = new LatLng(lat, lng);
            MarkerOptions markerOptions = createMarkOptions(loc, "Current Position", null);
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
            .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result =
            LocationServices.SettingsApi.checkLocationSettings(mApiClient, builder.build());
        result.setResultCallback(this);

        LocationServices.FusedLocationApi.requestLocationUpdates(mApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        // TODO: Pending
        Log.d(TAG, "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // TODO: Pending
        Log.d(TAG, "onConnectionFailed");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged");

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        // Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = createMarkOptions(latLng, "Current Position",
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        // Move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(DEFAULT_ZOOM));

        // Stop location updates
        if (mApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mApiClient, this);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Log.d(TAG, "onMyLocationButtonClick");

        view.makeToast("MyLocation button clicked");
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        Log.d(TAG, "onResult");

        final Status status = locationSettingsResult.getStatus();
        final LocationSettingsStates state = locationSettingsResult.getLocationSettingsStates();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                // All location settings are satisfied. The client can initialize location
                // requests here.
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                // Location settings are not satisfied. But could be fixed by showing the user
                // a dialog.
                // Show the dialog by calling startResolutionForResult(),
                // and check the result in onActivityResult().
                view.showLocationDialog(status);
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                // Location settings are not satisfied. However, we have no way to fix the
                // settings so we won't show the dialog.
                break;
        }
    }

    public void updateLocation(GeoInfo geoInfo) {
        Log.d(TAG, "updateLocation");

        LatLng placeCoords = new LatLng(geoInfo.getPlaceCoordinates().getLatitude(),
            geoInfo.getPlaceCoordinates().getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(placeCoords));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(DEFAULT_ZOOM));

        for(GeoStation station: geoInfo.getStations()) {
            LatLng stationCoords = new LatLng(station.getCoordinates().getLatitude(),
                station.getCoordinates().getLongitude());
            String countryCode = Locale.getDefault().getCountry().toLowerCase();
            String markTitle = station.getName(countryCode) != null ? station.getName(countryCode) :
                geoInfo.getPlaceName();
            MarkerOptions markerOptions = createMarkOptions(stationCoords, markTitle,
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
            mCurrLocationMarker = mMap.addMarker(markerOptions);
        }
    }

    private MarkerOptions createMarkOptions(LatLng coords, String title, BitmapDescriptor icon) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(coords);
        markerOptions.title(title);
        if(icon != null) {
            markerOptions.icon(icon);
        }

        return markerOptions;
    }
}
