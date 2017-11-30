package com.example.michael.assignment5.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.michael.assignment5.data.Currently;

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

    public DarkSkyWeatherService(WeatherServiceCallback callback){
        this.callback = callback;
    }
    public String getLocation(){
        return location;
    }
    public void refreshWeather(String location){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                //String YQL = String.format("");
                String endpoint = String.format("https://api.darksky.net/forecast/423940d65ba228a7ac1119e2861fe408/42.3601,-71.0589");
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
                    JSONObject queryResults = data.optJSONObject("query");
//                    int code = queryResults.optInt("code");
//                    if(code == 400){
//                        callback.serviceFailure(new LocationWeatherException("This location does not exist."));
//                        return;
//                    }
                    Currently currently = new Currently();
                    currently.populate(data.optJSONObject("currently"));

                    callback.serviceSuccess(currently);
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
