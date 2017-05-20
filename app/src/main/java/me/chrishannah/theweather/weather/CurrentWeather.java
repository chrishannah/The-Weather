package me.chrishannah.theweather.weather;

import org.json.JSONObject;

import me.chrishannah.theweather.util.JSONData;

/**
 * Created by christopher on 04/04/2017.
 */

public final class CurrentWeather {
    JSONData jsonData = new JSONData();

    public Long time;
    public String summary;
    public Double nearestStormDistance;
    public Double precipitationIntensity;
    public Double precipitationProbability;
    public String precipitationType;
    public Double temperature;
    public Double apparentTemperature;
    public Double humidity;
    public Double windSpeed;
    public Double windBearing;
    public Double visibility;
    public Double cloudCover;
    public Double pressure;
    public Double ozone;

    CurrentWeather(JSONObject data) {
        time = jsonData.getLong(data, "time");
        summary = jsonData.getString(data, "summary");
        nearestStormDistance = jsonData.getDouble(data, "nearestStormDistance");
        precipitationIntensity = jsonData.getDouble(data, "precipIntensity");
        precipitationProbability = jsonData.getDouble(data, "precipProbability");
        precipitationType = jsonData.getString(data, "precipType");
        temperature = jsonData.getDouble(data, "temperature");
        apparentTemperature = jsonData.getDouble(data, "apparentTemperature");
        humidity = jsonData.getDouble(data, "humidity");
        windSpeed = jsonData.getDouble(data, "windSpeed");
        windBearing = jsonData.getDouble(data, "windBearing");
        visibility = jsonData.getDouble(data, "visibility");
        cloudCover = jsonData.getDouble(data, "cloudCover");
        pressure = jsonData.getDouble(data, "pressure");
        ozone = jsonData.getDouble(data, "ozone");
    }
}
