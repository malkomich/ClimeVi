package com.career.talentomobile.climeviewer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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
    private FrameLayout weatherLayout;
    private FrameLayout temperatureSection;
    private FrameLayout humiditySection;

    private ProgressBar temperatureBar;
    private TextView temperatureText;
    private FloatingActionButton temperatureButton;

    private ProgressBar humidityBar;
    private TextView humidityText;
    private FloatingActionButton humidityButton;

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

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        weatherLayout = (FrameLayout) view.findViewById(R.id.weatherLayout);
        temperatureSection = (FrameLayout) view.findViewById(R.id.temperatureLayout);
        humiditySection = (FrameLayout) view.findViewById(R.id.humidityLayout);

        temperatureBar = (ProgressBar) view.findViewById(R.id.temperatureBar);
        temperatureText = (TextView) view.findViewById(R.id.temperatureText);
        temperatureButton = (FloatingActionButton) view.findViewById(R.id.temperatureButton);

        humidityBar = (ProgressBar) view.findViewById(R.id.humidityBar);
        humidityText = (TextView) view.findViewById(R.id.humidityText);
        humidityButton = (FloatingActionButton) view.findViewById(R.id.humidityButton);

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
        weatherLayout.setVisibility(View.VISIBLE);
        temperatureSection.setVisibility(View.VISIBLE);
    }
}
