package com.example.michael.assignment5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NextFiveHours extends AppCompatActivity {
    private String coordinates;
    private TextView temperatureTextView;
    private Button returnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_five_hours);

        coordinates = getIntent().getStringExtra("coordinates");
        temperatureTextView = (TextView) findViewById(R.id.temperatures);
        returnButton = (Button) findViewById(R.id.returnButton);

    }
}
