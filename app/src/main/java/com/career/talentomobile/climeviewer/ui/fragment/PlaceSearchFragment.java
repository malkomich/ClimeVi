package com.career.talentomobile.climeviewer.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.callback.OnPlaceUpdatedListener;
import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.presenter.PlaceSearchPresenter;
import com.career.talentomobile.climeviewer.ui.view.PlaceSearchView;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

/**
 * Created by malkomich on 03/10/2016.
 */
public class PlaceSearchFragment extends BaseFragment implements PlaceSearchView {

    private static final String TAG = PlaceSearchFragment.class.getName();

    private PlaceSearchPresenter presenter;
    private OnPlaceUpdatedListener onPlaceUpdatedListenerListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PlaceSearchPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_place_autocomplete, container, false);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
            getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete);
        autocompleteFragment.setOnPlaceSelectedListener(presenter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            onPlaceUpdatedListenerListener = (OnPlaceUpdatedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnPlaceUpdatedListener");
        }
    }

    @Override
    public void updatePlace(GeoInfo geoInfo) {
        onPlaceUpdatedListenerListener.onPlaceUpdated(geoInfo);
    }
}
