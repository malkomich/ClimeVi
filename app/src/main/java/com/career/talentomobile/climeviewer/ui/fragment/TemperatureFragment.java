package com.career.talentomobile.climeviewer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.Util;
import com.career.talentomobile.climeviewer.model.GeoPoints;
import com.career.talentomobile.climeviewer.presenter.TemperaturePresenter;
import com.career.talentomobile.climeviewer.ui.view.TemperatureView;

/**
 * Created by malkomich on 04/10/2016.
 */

public class TemperatureFragment extends BaseFragment implements TemperatureView {

    private static final String TAG = TemperatureFragment.class.getName();

    private TemperaturePresenter presenter;
    private LinearLayout temperatureLayout;
    private ProgressBar temperatureBar;
    private TextView temperatureText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TemperaturePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temp_bar, container, false);
        temperatureLayout = (LinearLayout) view.findViewById(R.id.weatherLayout);
        temperatureBar = (ProgressBar) view.findViewById(R.id.temperatureBar);
        temperatureText = (TextView) view.findViewById(R.id.temperatureText);

        return view;
    }

    @Override
    public void updateWeather(GeoPoints area) {
        presenter.updateWeather(area);
    }

    @Override
    public void setTemperature(double temperature) {
        Log.d(TAG, "setTemperature: " + temperature);
        temperatureBar.setProgress((int) Util.celsiusToFahrenheit(temperature));
        temperatureText.setText(temperature + "º");
        temperatureLayout.setVisibility(View.VISIBLE);
    }
}
