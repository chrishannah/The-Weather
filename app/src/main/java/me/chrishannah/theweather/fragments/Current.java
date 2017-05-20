package me.chrishannah.theweather.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import me.chrishannah.theweather.R;
import me.chrishannah.theweather.util.Location;
import me.chrishannah.theweather.weather.CurrentWeather;
import me.chrishannah.theweather.weather.Weather;
import me.chrishannah.theweather.weather.WeatherCallback;
import me.chrishannah.theweather.weather.WeatherData;

public class Current extends Fragment {
    private OnFragmentInteractionListener mListener;

    // Weather Data
    Weather weather;
    WeatherData weatherData;

    // Views
    TextView locationView;
    TextView timeUpdatedView;

    TextView summaryView;
    TextView temperatureView;
    TextView apparentTemperatureView;

    CardView extrasCard;
    LinearLayout extrasLayout;


    public Current() {
    }

    public static Current newInstance() {
        final Current fragment = new Current();
        return fragment;
    }

    void updateWeatherData() {
        if (weather == null) {
            weather = new Weather(this.getContext());
        }
        if (weatherData == null) {
            this.weather.getWeather(new WeatherCallback() {
                @Override
                public void onSuccess(WeatherData data) {
                    weatherData = data;
                    loadViewsWithData();
                }

                @Override
                public void onError() {
                }
            });
        } else {
            loadViewsWithData();
        }
    }

    private void loadViewsWithData() {
        Location location = new Location(weatherData.latitude, weatherData.longitude);
        locationView.setText("Location: " + location.toString());

        Date date  = new Date(weatherData.current.time *1000);
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM, Locale.getDefault());
        String dateString = dateFormat.format(date);
        timeUpdatedView.setText("Data Updated: " + dateString);

        summaryView.setText(weatherData.current.summary + " - " + weatherData.hourly.summary);
        temperatureView.setText(weatherData.current.temperature.intValue() + "°");
        apparentTemperatureView.setText("Feels like " + weatherData.current.apparentTemperature.intValue() + "°");

        ArrayList<String> extras = getExtraData(weatherData.current);

        System.out.print("Extras size: " + extras.size());

        if (extras.isEmpty()) {
            extras.add("No more information received.");
        }

        // Detailed Information
        // Clear list first, then repopulate with data

        extrasLayout.removeAllViewsInLayout();

        // Add a Text view for each bit of extra information recieved
        for (String entry : extras) {
            TextView newEntry = new TextView(getContext());
            newEntry.setText(entry);
            newEntry.setTextSize(16f);
            newEntry.setPadding(2, 8, 2, 8);
            newEntry.setTextColor(0xFF111111);
            extrasLayout.addView(newEntry);
        }


        extrasCard.forceLayout();
        extrasCard.requestLayout();

    }


    private ArrayList<String> getExtraData(CurrentWeather current) {

        ArrayList<String> extraInformation = new ArrayList<>();
        // Precipitation
        if (current.precipitationType != null) {
            String type = current.precipitationType.substring(0, 1).toUpperCase() + current.precipitationType.substring(1).toLowerCase();

            extraInformation.add("Precipitation Type: " + type);
            extraInformation.add("Precipitation Intensity: " + current.precipitationIntensity);
            extraInformation.add("Precipitation Probability: " + current.precipitationProbability);
        }

        // Humidity
        if (current.humidity != null) {
            extraInformation.add("Humidity: " + current.humidity);
        }

        // Wind
        if (current.windSpeed != null) {
            extraInformation.add("Wind Speed: " + current.windSpeed);
            extraInformation.add("Wind Bearing: " + current.windBearing);
        }

        // Visibility
        if (current.visibility != null) {
            extraInformation.add("Visibility: " + current.visibility);
        }

        // Nearest Storm Distance
        if (current.nearestStormDistance != null) {
            extraInformation.add("Nearest Storm Distance: " + current.nearestStormDistance);
        }

        // Cloud Cover
        if (current.cloudCover != null) {
            extraInformation.add("Cloud Cover: " + current.cloudCover);
        }

        // Pressure
        if (current.pressure != null) {
            extraInformation.add("Pressure: " + current.pressure);
        }

        // Ozone
        if (current.ozone != null) {
            extraInformation.add("Ozone: " + current.ozone);
        }

        return extraInformation;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateWeatherData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate(R.layout.fragment_current, container,false);

        locationView = (TextView) myInflatedView.findViewById(R.id.locationView);
        timeUpdatedView = (TextView) myInflatedView.findViewById(R.id.timeView);

        summaryView = (TextView) myInflatedView.findViewById(R.id.summary);
        temperatureView = (TextView) myInflatedView.findViewById(R.id.temperature);
        apparentTemperatureView = (TextView) myInflatedView.findViewById(R.id.apparentTemperature);

        extrasCard = (CardView) myInflatedView.findViewById(R.id.extrasCard);
        extrasLayout = (LinearLayout) myInflatedView.findViewById(R.id.extrasVerticalLayout);

        updateWeatherData();

        return myInflatedView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            weather = new Weather(this.getContext());
            this.weather.getWeather(new WeatherCallback() {
                @Override
                public void onSuccess(WeatherData data) {
                    weatherData = data;
                    System.out.println(weatherData.current.summary  );
                    updateWeatherData();
                }

                @Override
                public void onError() {

                }
            });
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}