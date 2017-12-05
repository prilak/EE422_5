package com.example.michael.assignment5.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by michael on 12/4/17.
 */

public class TwoDay implements JSONPopulator{
    private JSONArray hours;
    private double averageTemp;

    @Override
    public void populate(JSONObject data) {
        double sum = 0;
        hours = data.optJSONArray("data");
        for(int i = 0; i < 48; i++){
            JSONObject hour = hours.optJSONObject(i);
            Double temp = hour.optDouble("temperature");
            sum += temp.doubleValue();
        }
        averageTemp = sum / 48;

    }

    public double getAverageTemp() {
        return averageTemp;
    }
}
