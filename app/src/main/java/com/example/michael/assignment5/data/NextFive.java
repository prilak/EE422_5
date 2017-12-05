package com.example.michael.assignment5.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by michael on 12/3/17.
 */

public class NextFive implements JSONPopulator{
    private JSONArray times;
    private ArrayList<Double> temperatures;
    @Override
    public void populate(JSONObject data) {

        temperatures = new ArrayList<Double>();
        times = data.optJSONArray("data");
        for(int i = 0; i < 5; i++){
            JSONObject time = times.optJSONObject(i);
            temperatures.add(time.optDouble("temperature"));
        }
    }

    public ArrayList<Double> getTemperatures() {
        return temperatures;
    }
}
