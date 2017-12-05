package com.example.michael.assignment5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.michael.assignment5.data.Currently;
import com.example.michael.assignment5.data.NextFive;
import com.example.michael.assignment5.data.PastTemperature;
import com.example.michael.assignment5.data.TwoDay;
import com.example.michael.assignment5.data.WeekForecast;
import com.example.michael.assignment5.service.DarkSkyWeatherService;
import com.example.michael.assignment5.service.WeatherServiceCallback;

import java.util.Locale;

public class PastTemperatureTemp extends AppCompatActivity implements WeatherServiceCallback{
    private static final int PAST_TEMPERATURE = 4;

    private EditText yearInput;
    private EditText monthInput;
    private EditText dayInput;
    private EditText hourInput;

    private Button startButton;
    private Button returnButton;

    private TextView temperature;
    private String coordinates;
    private DarkSkyWeatherService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_temperature);
        coordinates = getIntent().getStringExtra("coordinates");

        startButton = (Button) findViewById(R.id.startButton);
        returnButton = (Button) findViewById(R.id.returnButton);
        temperature = (TextView) findViewById(R.id.averageTemp);
        yearInput = (EditText) findViewById(R.id.yearInput);
        monthInput = (EditText) findViewById(R.id.monthInput);
        dayInput = (EditText) findViewById(R.id.dayInput);
        hourInput = (EditText) findViewById(R.id.hourInput);

        enableButtons(this);


    }

    private void enableButtons(final PastTemperatureTemp callback) {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = callback.getYearInput().getText().toString();
                String month = callback.getMonthInput().getText().toString();
                String day = callback.getDayInput().getText().toString();
                String hour = callback.getHourInput().getText().toString();
                String time = year + "-" + month + "-" + day + "T" + hour + ":00:00";
                //temperature.setText(time);
                service = new DarkSkyWeatherService(callback, PAST_TEMPERATURE, time);
                service.refreshWeather(coordinates);

            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PastTemperatureTemp.this, MainActivity.class);
                i.putExtra("coordinates", coordinates);
                startActivity(i);
            }
        });
    }

    public EditText getDayInput() {
        return dayInput;
    }

    public EditText getHourInput() {
        return hourInput;
    }
    public EditText getMonthInput() {
        return monthInput;
    }

    public EditText getYearInput() {
        return yearInput;
    }

    @Override
    public void serviceSuccess(Currently data) {

    }

    @Override
    public void serviceSuccess(NextFive data) {

    }

    @Override
    public void serviceSuccess(com.example.michael.assignment5.data.PastTemperature data) {
        String text = "Temperature: " + data.getTemperature();
        temperature.setText(text);
    }

    @Override
    public void serviceSuccess(TwoDay data) {

    }

    @Override
    public void serviceSuccess(WeekForecast data) {

    }

    @Override
    public void serviceFailure(Exception exception) {

    }
}
