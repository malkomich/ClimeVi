package com.github.malkomich.climeviewer.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.malkomich.climeviewer.R;
import com.github.malkomich.climeviewer.callback.Logger;
import com.github.malkomich.climeviewer.callback.OnPlaceUpdatedListener;
import com.github.malkomich.climeviewer.model.GeoInfo;
import com.github.malkomich.climeviewer.presenter.PlaceSearchPresenter;
import com.github.malkomich.climeviewer.ui.view.PlaceSearchView;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

/**
 * Fragment implementation of the place search section view.
 */
public class PlaceSearchFragment extends Fragment implements PlaceSearchView {

    private static final int NUMBER_OF_REQUESTS = 2;

    private PlaceSearchPresenter presenter;
    private OnPlaceUpdatedListener onPlaceUpdatedListener;
    private Logger logger;

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
            logger = (Logger) context;
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
        logger.logSearch(geoInfo.getPlaceName(), NUMBER_OF_REQUESTS);
        if(geoInfo.hasStations()) {
            presenter.saveRequest(geoInfo, onPlaceUpdatedListener.getHistorySharedPreferences());
        }
    }
}
