package com.example.michael.assignment5.data;

import org.json.JSONObject;

/**
 * Created by michael on 12/4/17.
 */

public class PastTemperature implements JSONPopulator{
    private Double temperature;
    @Override
    public void populate(JSONObject data) {
        temperature = data.optDouble("temperature");
    }

    public Double getTemperature() {
        return temperature;
    }
}
