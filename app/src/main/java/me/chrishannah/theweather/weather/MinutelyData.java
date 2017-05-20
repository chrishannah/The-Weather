package me.chrishannah.theweather.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.chrishannah.theweather.util.JSONData;

/**
 * Created by christopher on 04/04/2017.
 */

public final class MinutelyData {
    JSONData jsonData = new JSONData();

    public String summary;
    public MinutelyWeatherData[] data;

    MinutelyData(JSONObject data) {
        try {
            summary = jsonData.getString(data, "summary");
            JSONArray weatherData = data.getJSONArray("data");
            this.data = new MinutelyWeatherData[weatherData.length()];
            for (int i = 0; i < weatherData.length(); i++) {
                this.data[i] = new MinutelyWeatherData(weatherData.getJSONObject(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

final class MinutelyWeatherData {
    JSONData jsonData = new JSONData();

    public Long time;
    public Double precipitationIntensity;
    public Double precipitationProbability;
    public String precipitationType;

    MinutelyWeatherData(JSONObject data) {
        time = jsonData.getLong(data, "time");
        precipitationIntensity = jsonData.getDouble(data, "precipIntensity");
        precipitationProbability = jsonData.getDouble(data, "precipProbability");
        precipitationType = jsonData.getString(data, "precipType");
    }
}
