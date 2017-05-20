package me.chrishannah.theweather.weather;

import org.json.JSONException;
import org.json.JSONObject;

import me.chrishannah.theweather.util.JSONData;

/**
 * Created by Chris Hannah on 04/04/2017.
 */

public class WeatherData {

    // JSON Extracter Class
    JSONData jsonData = new JSONData();

    // Generic Weather Request Data
    public Double latitude;
    public Double longitude;
    public String timezone;

    // Actual Weather Data
    public CurrentWeather current;
    public MinutelyData minutely;
    public HourlyData hourly;
    public DailyData daily;

    WeatherData(JSONObject data) {
        try {
            latitude = jsonData.getDouble(data, "latitude");
            longitude = jsonData.getDouble(data, "longitude");
            timezone = jsonData.getString(data, "timezone");

            current = new CurrentWeather(data.getJSONObject("currently"));
            minutely = new MinutelyData(data.getJSONObject("minutely"));
            hourly = new HourlyData(data.getJSONObject("hourly"));
            daily = new DailyData(data.getJSONObject("daily"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}






