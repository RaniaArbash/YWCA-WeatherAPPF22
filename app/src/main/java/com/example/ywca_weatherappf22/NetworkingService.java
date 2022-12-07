package com.example.ywca_weatherappf22;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkingService {
    // fetch all cities
    // fetch weather in one city

    interface NetworkingListener{
        void gettingJsonIsCompleted(String json);
    }

    String weatherAPIKey = "071c3ffca10be01d334505630d2c1a9c";
    NetworkingListener listener;
    Handler handler = new Handler(Looper.getMainLooper());

    String cityURLString = "http://gd.geobytes.com/AutoCompleteCity?&q=";

    String weatherURL1 = "https://api.openweathermap.org/data/2.5/weather?q=" ;
    String weatherURL2 =  "&appid=071c3ffca10be01d334505630d2c1a9c";

    void getAllCities(String query){
        String fullString = cityURLString + query;
        connect(fullString);
    }
    void getWeatherInSelectedCity(City c){
        String fullString = weatherURL1 + c.city +","+c.country + weatherURL2;
        connect(fullString);
    }



    void connect(String urlString){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int value = 0;
                    URL url = new URL(urlString);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    StringBuffer buffer = new StringBuffer();

                    while((value = in.read()) != -1){
                        buffer.append((char)value);
                    }
                    // the json content is ready to returned back
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.gettingJsonIsCompleted(buffer.toString());
                        }
                    });



                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }


}
