package com.example.michael.assignment5.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by michael on 12/4/17.
 */

public class WeekForecast implements JSONPopulator{
    private ArrayList<String> temperatures;
    @Override
    public void populate(JSONObject data) {
        temperatures = new ArrayList<String>();
        JSONArray dates = data.optJSONArray("data");
        for(int i = 0; i < 7; i++){
            JSONObject date = dates.optJSONObject(i);
            temperatures.add("low: " + date.optDouble("temperatureLow") + "high: " + date.optDouble("temperatureHigh"));
        }
    }

    public ArrayList<String> getTemperatures() {
        return temperatures;
    }
}
