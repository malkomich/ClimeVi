package com.github.malkomich.climeviewer.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.github.malkomich.climeviewer.R;
import com.github.malkomich.climeviewer.adapter.HistoryAdapter;
import com.github.malkomich.climeviewer.callback.OnPlaceUpdatedListener;
import com.github.malkomich.climeviewer.model.GeoInfo;
import com.github.malkomich.climeviewer.presenter.HistoryPresenter;
import com.github.malkomich.climeviewer.ui.view.HistoryView;

import java.util.Collections;
import java.util.List;

/**
 * Fragment implementation of the history section view.
 */
public class HistoryFragment extends ListFragment implements HistoryView {

    private HistoryPresenter presenter;
    private ViewGroup hiddenPanel;
    private OnPlaceUpdatedListener onPlaceUpdatedListener;

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreate()
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HistoryPresenter(this);
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.ListFragment#onCreateView()
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        hiddenPanel = (ViewGroup) view.findViewById(R.id.history_panel);

        hiddenPanel.setOnTouchListener(presenter);

        return view;
    }

    /**
     * Fill the history adapter with the geolocation data objects.
     *
     * @param items
     *              List of geolocation data objects
     */
    @Override
    public void setItems(List<GeoInfo> items) {
        Collections.reverse(items);
        setListAdapter(new HistoryAdapter(getActivity(), R.layout.history_row, items));
    }

    /**
     * Retrieves the geolocation data of a specific place from the history adapter.
     *
     * @param position
     *                 Index of the GeoInfo object in the adapter
     * @return GeoInfo
     */
    @Override
    public GeoInfo getGeoInfoFromAdapter(int position) {
        return (GeoInfo) getListAdapter().getItem(position);
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
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.ListFragment#onListItemClick()
     */
    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        presenter.onItemClicked(position);
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

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.HistoryView#show()
     */
    @Override
    public void show() {
        presenter.loadHistory(onPlaceUpdatedListener.getHistorySharedPreferences());

        if(!getListAdapter().isEmpty()) {
            Animation bottomHalf = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_half);
            getListView().setAnimation(bottomHalf);
            hiddenPanel.setVisibility(View.VISIBLE);
        }
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.HistoryView#hide()
     */
    @Override
    public void hide() {
        Animation halfBottom = AnimationUtils.loadAnimation(getContext(), R.anim.half_bottom);
        halfBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                hiddenPanel.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        getListView().setAnimation(halfBottom);
        getListView().startAnimation(halfBottom);
    }
}
