package com.example.michael.assignment5;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michael.assignment5.data.Currently;

import com.example.michael.assignment5.service.DarkSkyWeatherService;
import com.example.michael.assignment5.service.WeatherServiceCallback;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    //private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView humidityTextView;

    private DarkSkyWeatherService service;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperatureTextView = (TextView)  findViewById(R.id.temperatureTextView);
        humidityTextView = (TextView)  findViewById(R.id.humidityTextView);

        service = new DarkSkyWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Austin, TX");

    }


    @Override
    public void serviceSuccess(Currently data) {
        temperatureTextView.setText(Double.toString(data.getTemperature()));
        humidityTextView.setText(Double.toString(data.getHumidity()));
        dialog.hide();
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
