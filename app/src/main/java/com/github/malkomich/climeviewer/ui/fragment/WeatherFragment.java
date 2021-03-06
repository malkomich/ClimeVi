package com.github.malkomich.climeviewer.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.malkomich.climeviewer.R;
import com.github.malkomich.climeviewer.util.MathUtils;
import com.github.malkomich.climeviewer.model.GeoPoints;
import com.github.malkomich.climeviewer.presenter.WeatherPresenter;
import com.github.malkomich.climeviewer.ui.view.WeatherView;

/**
 * Fragment implementation of the weather section view.
 */
public class WeatherFragment extends BaseFragment implements WeatherView, View.OnClickListener, View.OnLongClickListener {

    private static final int PROGRESS_SPEED = 3500;

    private WeatherPresenter presenter;
    private FrameLayout weatherLayout;
    private FrameLayout temperatureSection;
    private FrameLayout humiditySection;

    private ProgressBar temperatureBar;
    private TextView temperatureText;
    private ProgressBar humidityBar;
    private TextView humidityText;

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

        FloatingActionButton temperatureButton = (FloatingActionButton) view.findViewById(R.id.temperatureButton);
        temperatureButton.setOnClickListener(this);
        temperatureButton.setOnLongClickListener(this);

        humidityBar = (ProgressBar) view.findViewById(R.id.humidityBar);
        humidityText = (TextView) view.findViewById(R.id.humidityText);

        FloatingActionButton humidityButton = (FloatingActionButton) view.findViewById(R.id.humidityButton);
        humidityButton.setOnClickListener(this);
        humidityButton.setOnLongClickListener(this);

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
        weatherLayout.setVisibility(View.VISIBLE);
        temperatureSection.setVisibility(View.VISIBLE);

        temperatureText.setText(String.valueOf(MathUtils.roundDouble(temperature, 1)));

        int newProgressVal = (int) MathUtils.celsiusToFahrenheit(temperature);
        makeBarAnimation(temperatureBar, newProgressVal);
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.WeatherView#setHumidity()
     */
    @Override
    public void setHumidity(double humidity) {
        humidityText.setText(getString(R.string.percentage, (int) humidity));

        makeBarAnimation(humidityBar, (int) humidity);
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.WeatherView#noWeatherInfo()
     */
    @Override
    public void noWeatherInfo() {
        weatherLayout.setVisibility(View.GONE);
        makeToast(R.string.no_weather_data);
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick()
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.temperatureButton:
                temperatureSection.setVisibility(View.VISIBLE);
                humiditySection.setVisibility(View.INVISIBLE);
                break;
            case R.id.humidityButton:
                temperatureSection.setVisibility(View.INVISIBLE);
                humiditySection.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    /* (non-Javadoc)
     * @see android.view.View.OnLongClickListener #onLongClick()
     */
    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.temperatureButton:
                makeToast(R.string.temperature_button);
                break;
            case R.id.humidityButton:
                makeToast(R.string.humidity_button);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * Update a progress bar, with a decelerate animation.
     *
     * @param bar
     *            Progress bar
     * @param newProgressVal
     *                       New value to set in the bar
     */
    private void makeBarAnimation(ProgressBar bar, int newProgressVal) {
        ObjectAnimator animation = ObjectAnimator.ofInt(bar, "progress",
            bar.getProgress(), newProgressVal);
        animation.setDuration(PROGRESS_SPEED);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }
}
