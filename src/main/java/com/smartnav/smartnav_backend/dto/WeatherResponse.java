package com.smartnav.smartnav_backend.dto;

public class WeatherResponse {
    private String location;
    private double temperature;
    private String weather;
    private String time;

    public WeatherResponse() {
    }

    public WeatherResponse(
            String location,
            double temperature,
            String weather,
            String time) {

        this.location = location;
        this.temperature = temperature;
        this.weather = weather;
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
