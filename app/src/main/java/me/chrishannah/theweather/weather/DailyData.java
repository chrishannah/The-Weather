package me.chrishannah.theweather.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.chrishannah.theweather.util.JSONData;

/**
 * Created by christopher on 04/04/2017.
 */

public final class DailyData {
    JSONData jsonData = new JSONData();

    public String summary;
    public DailyWeatherData[] data;

    DailyData(JSONObject data) {
        try {
            summary = jsonData.getString(data, "summary");
            JSONArray weatherData = data.getJSONArray("data");
            this.data = new DailyWeatherData[weatherData.length()];
            for (int i = 0; i < weatherData.length(); i++) {
                this.data[i] = new DailyWeatherData(weatherData.getJSONObject(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

final class DailyWeatherData {
    JSONData jsonData = new JSONData();

    public Long time;
    public String summary;
    public Long sunriseTime;
    public Long sunsetTime;
    public Double moonPhase;
    public Double nearestStormDistance;
    public Double precipitationIntensity;
    public Double precipitationProbability;
    public String precipitationType;
    public Double temperatureMin;
    public Double temperatureMax;
    public Long temperatureMinTime;
    public Long temperatureMaxTime;
    public Double apparentTemperatureMin;
    public Double apparentTemperatureMax;
    public Double humidity;
    public Double windSpeed;
    public Double windBearing;
    public Double visibility;
    public Double cloudCover;
    public Double pressure;
    public Double ozone;

    DailyWeatherData(JSONObject data) {
        time = jsonData.getLong(data, "time");
        summary = jsonData.getString(data, "summary");
        sunriseTime = jsonData.getLong(data, "sunriseTime");
        sunsetTime = jsonData.getLong(data, "sunsetTime");
        moonPhase = jsonData.getDouble(data, "moonPhase");
        nearestStormDistance = jsonData.getDouble(data, "nearestStormDistance");
        precipitationIntensity = jsonData.getDouble(data, "precipIntensity");
        precipitationProbability = jsonData.getDouble(data, "precipProbability");
        precipitationType = jsonData.getString(data, "precipType");
        temperatureMin = jsonData.getDouble(data, "temperatureMin");
        temperatureMax = jsonData.getDouble(data, "temperatureMax");
        temperatureMinTime = jsonData.getLong(data, "temperatureMinTime");
        temperatureMaxTime = jsonData.getLong(data, "temperatureMaxTime");
        apparentTemperatureMin = jsonData.getDouble(data, "apparentTemperatureMin");
        apparentTemperatureMax = jsonData.getDouble(data, "apparentTemperatureMax");
        humidity = jsonData.getDouble(data, "humidity");
        windSpeed = jsonData.getDouble(data, "windSpeed");
        windBearing = jsonData.getDouble(data, "windBearing");
        visibility = jsonData.getDouble(data, "visibility");
        cloudCover = jsonData.getDouble(data, "cloudCover");
        pressure = jsonData.getDouble(data, "pressure");
        ozone = jsonData.getDouble(data, "ozone");
    }
}
