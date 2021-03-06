package com.example.michael.assignment5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michael.assignment5.data.Currently;
import com.example.michael.assignment5.data.NextFive;
import com.example.michael.assignment5.data.PastTemperature;
import com.example.michael.assignment5.data.TwoDay;
import com.example.michael.assignment5.data.WeekForecast;
import com.example.michael.assignment5.service.DarkSkyWeatherService;
import com.example.michael.assignment5.service.WeatherServiceCallback;

public class NextFiveHours extends AppCompatActivity implements WeatherServiceCallback{
    private static final int NEXT_FIVE_HOURS = 1;

    private String coordinates;
    private TextView temperatureTextView;
    private Button returnButton;
    private DarkSkyWeatherService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_five_hours);

        coordinates = getIntent().getStringExtra("coordinates");
        temperatureTextView = (TextView) findViewById(R.id.temperatures);
        returnButton = (Button) findViewById(R.id.returnButton);
        enableButtons();
        service = new DarkSkyWeatherService(this, NEXT_FIVE_HOURS, "");
        service.refreshWeather(coordinates);
    }

    private void enableButtons() {
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NextFiveHours.this, MainActivity.class);
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
        for(Double val: data.getTemperatures()){
            temperatureTextView.append("Temperature: " + val.toString() + "\n");
        }
    }

    @Override
    public void serviceSuccess(PastTemperature data) {

    }

    @Override
    public void serviceSuccess(TwoDay data) {

    }

    @Override
    public void serviceSuccess(WeekForecast data) {

    }

    @Override
    public void serviceFailure(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}
