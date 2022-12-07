package com.example.ywca_weatherappf22;

public class WeatherObject {
    String description;
    String icon;
    double temp;
    int humidity;
    double wind_speed;

    public WeatherObject() {
    }

    public WeatherObject(String description, String icon, double temp, int humidity, double wind_speed) {
        this.description = description;
        this.icon = icon;
        this.temp = temp;
        this.humidity = humidity;
        this.wind_speed = wind_speed;
    }
}
