package me.chrishannah.theweather.weather;

import android.content.Context;
import android.location.Geocoder;
import android.location.LocationListener;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import me.chrishannah.theweather.util.Location;

/**
 * Created by christopher on 04/04/2017.
 */

public class Weather {
    Context context;

    String url = "https://api.darksky.net/forecast/";
    String api_key = "fc087cba94be78c6c4a505678c2f7bb8";
    String units = "uk2";

    public Weather(Context context) {
        this.context = context;
    }

    public void getWeather(final WeatherCallback callback, Location location) {
        refreshWeatherData(this.context, callback, location);
    }

    private void refreshWeatherData(Context context, final WeatherCallback callback, Location location) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                createRequestURL(location),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(new WeatherData(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError();
                    }
                }
        );
        queue.add(jsonRequest);
    }

    private Location getLocation() {
        Geocoder geocoder = new Geocoder();
        Location
    }

    private String createRequestURL(Location location) {
        return baseURL + api_key + "/" + location.latitude + "," + location.latitude + "/";
    }

}

