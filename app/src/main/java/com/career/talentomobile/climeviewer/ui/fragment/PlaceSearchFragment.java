package com.career.talentomobile.climeviewer.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.callback.OnPlaceUpdatedListener;
import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.presenter.PlaceSearchPresenter;
import com.career.talentomobile.climeviewer.ui.view.PlaceSearchView;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

/**
 * Fragment implementation of the place search section view.
 */
public class PlaceSearchFragment extends Fragment implements PlaceSearchView {

    private PlaceSearchPresenter presenter;
    private OnPlaceUpdatedListener onPlaceUpdatedListener;

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreate()
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PlaceSearchPresenter(this);
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView()
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_place_autocomplete, container, false);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
            getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete);

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
            .build();
        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(presenter);

        return view;
    }

    /* (non-Javadoc)
     * @see android.app.Fragment#onAttach()
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            onPlaceUpdatedListener = (OnPlaceUpdatedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnPlaceUpdatedListener");
        }
    }

    /**
     * Delegates the place update to update also the involved fragments.
     *
     * @param geoInfo
     *                Geolocation data
     */
    @Override
    public void updatePlace(GeoInfo geoInfo) {
        onPlaceUpdatedListener.onPlaceUpdated(geoInfo);
        if(geoInfo.hasStations()) {
            presenter.saveRequest(geoInfo, onPlaceUpdatedListener.getHistorySharedPreferences());
        }
    }
}
