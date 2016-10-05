package com.career.talentomobile.climeviewer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.career.talentomobile.climeviewer.R;
import com.career.talentomobile.climeviewer.util.MathUtils;
import com.career.talentomobile.climeviewer.model.GeoPoints;
import com.career.talentomobile.climeviewer.presenter.WeatherPresenter;
import com.career.talentomobile.climeviewer.ui.view.WeatherView;

/**
 * Fragment implementation of the weather section view.
 */
public class WeatherFragment extends Fragment implements WeatherView, View.OnClickListener {

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
        temperatureButton.setOnClickListener(this);

        humidityBar = (ProgressBar) view.findViewById(R.id.humidityBar);
        humidityText = (TextView) view.findViewById(R.id.humidityText);
        humidityButton = (FloatingActionButton) view.findViewById(R.id.humidityButton);
        humidityButton.setOnClickListener(this);

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
        temperatureBar.setProgress((int) MathUtils.celsiusToFahrenheit(temperature));
        temperatureText.setText(String.valueOf(MathUtils.roundDouble(temperature, 1)));
        weatherLayout.setVisibility(View.VISIBLE);
        temperatureSection.setVisibility(View.VISIBLE);
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.WeatherView#setHumidity()
     */
    @Override
    public void setHumidity(double humidity) {
        humidityBar.setProgress((int) humidity);
        humidityText.setText(String.valueOf(MathUtils.roundDouble(humidity, 1)));
    }

    /* (non-Javadoc)
     * @see com.career.talentomobile.climeviewer.ui.view.WeatherView#noWeatherInfo()
     */
    @Override
    public void noWeatherInfo() {
        weatherLayout.setVisibility(View.GONE);
        Toast.makeText(getContext(), R.string.no_weather_data, Toast.LENGTH_SHORT).show();
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
}
