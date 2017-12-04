package com.example.michael.assignment5.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.michael.assignment5.data.Currently;
import com.example.michael.assignment5.data.NextFive;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by michael on 11/29/17.
 */

public class DarkSkyWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;
    private int code;
    private String date;
    public DarkSkyWeatherService(WeatherServiceCallback callback, int code, String date){
        this.callback = callback;
        this.code = code;
        this.date = date;
    }
    public String getLocation(){
        return location;
    }
    public void refreshWeather(final String location){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String endpoint;
                //String YQL = String.format("");
                if(code <= 3){
                    endpoint = String.format("https://api.darksky.net/forecast/423940d65ba228a7ac1119e2861fe408/" + location);

                } else {
                    endpoint = String.format("https://api.darksky.net/forecast/423940d65ba228a7ac1119e2861fe408/" + location + "," + date);

                }
                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();
                } catch (MalformedURLException e) {
                    error = e;
                } catch (IOException e) {
                    error = e;
                }
                return null;
            }
            @Override
            protected void onPostExecute(String s){

                if(s == null && error != null) {
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);
                    switch (code){
                        case 0:
                            Currently currently = new Currently();
                            currently.populate(data.optJSONObject("currently"));
                            callba ck.serviceSuccess(currently);
                            break;
                        case 1:
//                            NextFive nextFive = new NextFive();
//                            nextFive.populate(data.optJSONObject("hourly"));
//                            callback.serviceSuccess(nextFive);
                        case 2:
                        case 3:
                        case 4:
                    }

                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }
            }
        }.execute(location);
    }

    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String detailMessage){
            super(detailMessage);
        }
    }
}
