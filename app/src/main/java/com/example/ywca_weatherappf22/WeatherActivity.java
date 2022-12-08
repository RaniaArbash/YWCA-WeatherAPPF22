package com.example.ywca_weatherappf22;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity
implements NetworkingService.NetworkingListener{

    TextView tempText;
    TextView humidityText;
    TextView windspeedText;
    TextView descText;

    ImageView weatherIcon;
    WeatherObject wo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        tempText = findViewById(R.id.temp);
        humidityText = findViewById(R.id.humidity);
        windspeedText = findViewById(R.id.windspeed);
        weatherIcon = findViewById(R.id.weathericon);
        descText = findViewById(R.id.desc);
        if( getIntent().hasExtra("city")){
            City c = getIntent().getParcelableExtra("city");
            this.setTitle(c.city + " " + c.country);
            ((MyApp) getApplication()).networkingService.listener = this;
            ((MyApp) getApplication()).networkingService.getWeatherInSelectedCity(c);

        }
    }

    @Override
    public void gettingJsonIsCompleted(String json) {
         wo = JsonService.fromJsonToWeather(json);
        ((MyApp) getApplication()).networkingService.gettingImage(wo.icon);

    }

    @Override
    public void gettingImageIsCompleted(Bitmap image) {
        weatherIcon.setImageBitmap(image);
        tempText.setText("Temp is : " + String.format("%.2f",(wo.temp - 273)) + " C");
        humidityText.setText("Humidity is : " + wo.humidity + " %");
        windspeedText.setText("Wind Speed is : " + wo.wind_speed + "m/sec");
        descText.setText(  wo.description);

    }
}