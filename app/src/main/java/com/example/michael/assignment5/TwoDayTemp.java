package com.example.michael.assignment5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michael.assignment5.data.*;
import com.example.michael.assignment5.data.PastTemperature;
import com.example.michael.assignment5.service.DarkSkyWeatherService;
import com.example.michael.assignment5.service.WeatherServiceCallback;

import java.util.Locale;

public class TwoDayTemp extends AppCompatActivity implements WeatherServiceCallback{
    private static final int AVERAGE_TWO_DAYS = 2;
    private String coordinates;
    private Button returnButton;
    private TextView averageTemperature;
    private DarkSkyWeatherService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_day);
        coordinates = getIntent().getStringExtra("coordinates");

        returnButton = (Button) findViewById(R.id.returnButton);
        averageTemperature = (TextView) findViewById(R.id.averageTemp);

        enableButtons();

        service = new DarkSkyWeatherService(this, AVERAGE_TWO_DAYS,"");
        service.refreshWeather(coordinates);

    }

    private void enableButtons() {
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TwoDayTemp.this, MainActivity.class);
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
    public void serviceSuccess(com.example.michael.assignment5.data.TwoDay data) {
        averageTemperature.append(String.format(Locale.ENGLISH, "%.04f", data.getAverageTemp()));

    }

    @Override
    public void serviceSuccess(WeekForecast data) {

    }

    @Override
    public void serviceFailure(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}
