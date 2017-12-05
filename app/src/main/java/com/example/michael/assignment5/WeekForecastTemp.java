package com.example.michael.assignment5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michael.assignment5.data.Currently;
import com.example.michael.assignment5.data.NextFive;
import com.example.michael.assignment5.data.PastTemperature;
import com.example.michael.assignment5.data.TwoDay;
import com.example.michael.assignment5.data.WeekForecast;
import com.example.michael.assignment5.service.DarkSkyWeatherService;
import com.example.michael.assignment5.service.WeatherServiceCallback;

public class WeekForecastTemp extends AppCompatActivity implements WeatherServiceCallback{
    private static final int WEEKLY_FORECAST = 3;
    private Button returnButton;
    private TextView weekText;
    private String coordinates;
    private DarkSkyWeatherService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_forecast);

        coordinates = getIntent().getStringExtra("coordinates");


        returnButton = (Button) findViewById(R.id.returnButton);
        weekText = (TextView) findViewById(R.id.weeklyText);

        enableButtons();

        service = new DarkSkyWeatherService(this, WEEKLY_FORECAST,"");
        service.refreshWeather(coordinates);


    }

    private void enableButtons() {
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WeekForecastTemp.this, MainActivity.class);
                i.putExtra("coordinates", coordinates);
                startActivity(i);
            }
        });
    }

    @Override
    public void serviceSuccess(Currently data) {

    }

    @Override
    public void serviceSuccess(NextFive data) {

    }

    @Override
    public void serviceSuccess(PastTemperature data) {

    }

    @Override
    public void serviceSuccess(TwoDay data) {

    }

    @Override
    public void serviceSuccess(WeekForecast data) {
        for(String temp: data.getTemperatures()){
            weekText.append(temp + "\n");
        }

    }

    @Override
    public void serviceFailure(Exception exception) {

    }
}
