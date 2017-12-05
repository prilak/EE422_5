package com.example.michael.assignment5.service;


import com.example.michael.assignment5.data.Currently;
import com.example.michael.assignment5.data.JSONPopulator;
import com.example.michael.assignment5.data.NextFive;
import com.example.michael.assignment5.data.PastTemperature;
import com.example.michael.assignment5.data.TwoDay;
import com.example.michael.assignment5.data.WeekForecast;


/**
 * Created by michael on 11/29/17.
 */

public interface WeatherServiceCallback {
    void serviceSuccess(Currently data);
    void serviceSuccess(NextFive data);
    void serviceSuccess(PastTemperature data);
    void serviceSuccess(TwoDay data);
    void serviceSuccess(WeekForecast data);
    void serviceFailure(Exception exception);

}
