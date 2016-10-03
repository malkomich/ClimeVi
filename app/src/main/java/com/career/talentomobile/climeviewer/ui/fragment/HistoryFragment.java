package com.career.talentomobile.climeviewer.ui.fragment;

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

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.adapter.HistoryAdapter;
import com.career.talentomobile.climeviewer.callback.OnPlaceUpdatedListener;
import com.career.talentomobile.climeviewer.model.GeoInfo;
import com.career.talentomobile.climeviewer.presenter.HistoryPresenter;
import com.career.talentomobile.climeviewer.ui.view.HistoryView;

import java.util.List;

/**
 * Created by malkomich on 03/10/2016.
 */

public class HistoryFragment extends ListFragment implements HistoryView {

    private static final String TAG = HistoryFragment.class.getName();

    private HistoryPresenter presenter;
    private ViewGroup hiddenPanel;
    private OnPlaceUpdatedListener onPlaceUpdatedListener;
    private boolean fragmentShown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HistoryPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        hiddenPanel = (ViewGroup) view.findViewById(R.id.history_panel);

        hiddenPanel.setOnTouchListener(presenter);

        return view;
    }

    @Override
    public void setItems(List<GeoInfo> items) {
        setListAdapter(new HistoryAdapter(getActivity(), R.layout.history_row, items));
    }

    @Override
    public GeoInfo getGeoInfoFromAdapter(int position) {
        return (GeoInfo) getListAdapter().getItem(position);
    }

    @Override
    public void updatePlace(GeoInfo geoInfo) {
        onPlaceUpdatedListener.onPlaceUpdated(geoInfo);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        presenter.onItemClicked(position);
    }

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

    @Override
    public void show() {
        presenter.loadHistory(getContext());
        fragmentShown = true;

        Animation bottomHalf = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_half);
        getListView().setAnimation(bottomHalf);
        hiddenPanel.setVisibility(View.VISIBLE);
    }

    public boolean isShown() {
        return fragmentShown;
    }

    @Override
    public void hide() {
        Animation halfBottom = AnimationUtils.loadAnimation(getContext(), R.anim.half_bottom);
        halfBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                hiddenPanel.setVisibility(View.INVISIBLE);
                fragmentShown = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        getListView().setAnimation(halfBottom);
        getListView().startAnimation(halfBottom);
    }
}
