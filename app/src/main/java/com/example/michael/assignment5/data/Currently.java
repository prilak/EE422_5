package com.example.michael.assignment5.data;

import org.json.JSONObject;

/**
 * Created by michael on 11/29/17.
 */

public class Currently implements JSONPopulator{
    private double temperature;
    private double humidity;
    private double windspeed;
    private double precipitation;
    @Override
    public void populate(JSONObject data){
        temperature = data.optDouble("temperature");
        humidity = data.optDouble("humidity");
        precipitation = data.optDouble("precipIntensity");
        windspeed = data.optDouble("windSpeed");
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getWindspeed(){
        return windspeed;
    }
    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}
