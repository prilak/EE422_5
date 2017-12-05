package com.example.michael.assignment5;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michael.assignment5.data.Currently;

import com.example.michael.assignment5.data.JSONPopulator;
import com.example.michael.assignment5.data.NextFive;
import com.example.michael.assignment5.data.PastTemperature;
import com.example.michael.assignment5.data.TwoDay;
import com.example.michael.assignment5.data.WeekForecast;
import com.example.michael.assignment5.service.DarkSkyWeatherService;
import com.example.michael.assignment5.service.WeatherServiceCallback;


import java.util.Locale;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {
    private final int CURRENT_WEATHER = 0;
    private final int NEXT_FIVE_HOURS = 1;
    private final int AVERAGE_TWO_DAYS = 2;
    private final int WEEKLY_FORECAST = 3;
    private final int PAST_TEMPERATURE = 4;
    private TextView temperatureTextView;
    private TextView humidityTextView;

    private DarkSkyWeatherService service;
    private ProgressDialog dialog;
    private String coordinates;


    private Button currentWeather;
    private Button coordinatesButton;
    private Button nextFiveButton;
    private Button pastTemperatureButton;
    private Button twoDayButton;
    private Button weekForecastButton;
    @Override
    protected void onResume() {
        super.onResume();
        coordinates = getIntent().getStringExtra("coordinates");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinates = "42.3601,-71.0589";
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        humidityTextView = (TextView) findViewById(R.id.humidityTextView);

        currentWeather = (Button) findViewById(R.id.currentWeather);
        nextFiveButton = (Button) findViewById(R.id.nextFiveHours);
        pastTemperatureButton = (Button) findViewById(R.id.pastWeather);
        twoDayButton = (Button) findViewById(R.id.averageTwoDays);
        weekForecastButton = (Button) findViewById(R.id.nextWeek);
        // Location services
        coordinatesButton = (Button) findViewById(R.id.button3);

        service = new DarkSkyWeatherService(this, CURRENT_WEATHER, "");
//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading...");
//        dialog.show();

        service.refreshWeather(coordinates);
        enableButtons();

    }

    private void enableButtons() {
        currentWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCurrentWeatherActivity();
            }
        });
        coordinatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocationCoordinates.class);
                startActivity(intent);

            }
        });

        nextFiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NextFiveHours.class);
                i.putExtra("coordinates", coordinates);
                startActivity(i);
            }
        });
        twoDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TwoDayTemp.class);
                i.putExtra("coordinates", coordinates);
                startActivity(i);
            }
        });
        weekForecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WeekForecastTemp.class);
                i.putExtra("coordinates", coordinates);
                startActivity(i);
            }
        });
        pastTemperatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PastTemperatureTemp.class);
                i.putExtra("coordinates", coordinates);
                startActivity(i);
            }
        });

    }


    private void goToCurrentWeatherActivity(){
        Intent intent = new Intent( this, CurrentWeather.class);
        intent.putExtra("coordinates", coordinates);
        startActivity(intent);
    }


    @Override
    public void serviceSuccess(Currently data) {
        temperatureTextView.setText(Double.toString(data.getTemperature()));
        humidityTextView.setText(String.format(Locale.ENGLISH, "humidity is %.4f", data.getHumidity()));
//        dialog.hide();
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

    }

    @Override
    public void serviceFailure(Exception exception) {
//        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
