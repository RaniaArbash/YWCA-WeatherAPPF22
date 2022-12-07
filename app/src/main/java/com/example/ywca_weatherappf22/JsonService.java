package com.example.ywca_weatherappf22;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {

    static ArrayList<City> fromJSONToList(String jsonString){
        ArrayList<City> list = new ArrayList<>(0);
        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0 ; i< jsonArray.length(); i++){
               Log.d("weathee_app", jsonArray.getString(i));
                list.add(new City(jsonArray.getString(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    static WeatherObject fromJsonToWeather(String jsonString){
        WeatherObject wo = new WeatherObject();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            wo.description =  weatherArray.getJSONObject(0).getString("description");
            wo.icon =  weatherArray.getJSONObject(0).getString("icon");


        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Your task for today
        return wo;
    }
}
