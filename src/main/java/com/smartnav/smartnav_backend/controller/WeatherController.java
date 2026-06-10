package com.smartnav.smartnav_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartnav.smartnav_backend.dto.WeatherResponse;
import com.smartnav.smartnav_backend.service.WeatherService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(
            WeatherService weatherService) {

        this.weatherService = weatherService;
    }

    @GetMapping("/test")
    public String test() {

        return weatherService.getApiKey();
    }

    @GetMapping("/current")
    public WeatherResponse currentWeather(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam(defaultValue = "en")
            String lang) {

        return weatherService.getWeather(
                lat,
                lon,
                lang);
    }
}