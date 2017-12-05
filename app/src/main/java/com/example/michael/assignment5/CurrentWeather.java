package com.example.michael.assignment5;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michael.assignment5.data.Currently;
import com.example.michael.assignment5.data.JSONPopulator;
import com.example.michael.assignment5.service.DarkSkyWeatherService;

public class CurrentWeather extends MainActivity {
    private static final int CURRENT_WEATHER = 0;


    private TextView temperatureTextView;
    private TextView humidityTextView;
    private TextView windspeedTextView;
    private TextView precipitationTextView;
    private DarkSkyWeatherService service;
    private ProgressDialog dialog;
    private String coordinates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

        coordinates = getIntent().getStringExtra("coordinates");

        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        humidityTextView = (TextView) findViewById(R.id.humidityTextView);
        windspeedTextView = (TextView) findViewById(R.id.windspeedTextView);
        precipitationTextView = (TextView) findViewById(R.id.precipitationTextView);
        service = new DarkSkyWeatherService(this, CURRENT_WEATHER, "");

        //dialog = new ProgressDialog(this);
        //dialog.setMessage("Loading...");
        //dialog.show();



        service.refreshWeather(coordinates);
        Button currentWeather = (Button) findViewById(R.id.home);
        currentWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });
    }
    private void goToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("coordinates", coordinates);
        startActivity(intent);

    }

    @Override
    public void serviceSuccess(Currently data) {
        temperatureTextView.setText("Temperature: " + Double.toString(data.getTemperature()));
        humidityTextView.setText("Humidity: " + Double.toString(data.getHumidity()));
        windspeedTextView.setText("Wind Speed: " + Double.toString(data.getWindspeed()));
        precipitationTextView.setText("Precipitation: " + Double.toString(data.getPrecipitation()));

    }
    @Override
    public void serviceFailure(Exception exception) {
        //dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
