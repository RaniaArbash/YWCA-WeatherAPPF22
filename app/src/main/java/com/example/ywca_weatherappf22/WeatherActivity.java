package com.example.ywca_weatherappf22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class WeatherActivity extends AppCompatActivity
implements NetworkingService.NetworkingListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        if( getIntent().hasExtra("city")){
            ((MyApp) getApplication()).networkingService.listener = this;
            ((MyApp) getApplication()).networkingService.getWeatherInSelectedCity(getIntent().getParcelableExtra("city"));
        }
    }

    @Override
    public void gettingJsonIsCompleted(String json) {

    }
}