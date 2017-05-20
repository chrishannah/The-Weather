package me.chrishannah.theweather.weather;

public interface WeatherCallback {
    void onSuccess(WeatherData data);
    void onError();
}
