package me.chrishannah.theweather.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by christopher on 04/04/2017.
 */

public class JSONData {
    public Long getLong(JSONObject data, String name) {
        if (!data.isNull(name)) {
            try {
                return data.getLong(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public String getString(JSONObject data, String name) {
        if (!data.isNull(name)) {
            try {
                return data.getString(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public Double getDouble(JSONObject data, String name) {
        if (!data.isNull(name)) {
            try {
                return data.getDouble(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}