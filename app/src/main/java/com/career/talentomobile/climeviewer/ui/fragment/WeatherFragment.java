package com.career.talentomobile.climeviewer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.util.MathUtils;
import com.career.talentomobile.climeviewer.model.GeoPoints;
import com.career.talentomobile.climeviewer.presenter.WeatherPresenter;
import com.career.talentomobile.climeviewer.ui.view.WeatherView;

/**
 * Fragment implementation of the weather section view.
 */
public class WeatherFragment extends Fragment implements WeatherView {

    private static final String TAG = WeatherFragment.class.getName();

    private WeatherPresenter presenter;
    private LinearLayout temperatureLayout;
    private ProgressBar temperatureBar;
    private TextView temperatureText;

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreate()
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new WeatherPresenter(this);
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView()
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temp_bar, container, false);
        temperatureLayout = (LinearLayout) view.findViewById(R.id.weatherLayout);
        temperatureBar = (ProgressBar) view.findViewById(R.id.temperatureBar);
        temperatureText = (TextView) view.findViewById(R.id.temperatureText);

        return view;
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.WeatherView#updateWeather()
     */
    @Override
    public void updateWeather(GeoPoints area) {
        presenter.updateWeather(area);
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.WeatherView#setTemperature()
     */
    @Override
    public void setTemperature(double temperature) {
        Log.d(TAG, "setTemperature: " + temperature);

        temperatureBar.setProgress((int) MathUtils.celsiusToFahrenheit(temperature));
        temperatureText.setText(String.valueOf(MathUtils.roundDouble(temperature, 1)));
        temperatureLayout.setVisibility(View.VISIBLE);
    }
}
