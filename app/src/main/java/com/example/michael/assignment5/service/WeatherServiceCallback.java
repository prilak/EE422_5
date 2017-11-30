package com.example.michael.assignment5.service;


import com.example.michael.assignment5.data.Currently;


/**
 * Created by michael on 11/29/17.
 */

public interface WeatherServiceCallback {
    void serviceSuccess(Currently data);
    void serviceFailure(Exception exception);

}
