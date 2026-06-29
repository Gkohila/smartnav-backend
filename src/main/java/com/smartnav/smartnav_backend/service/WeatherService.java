package com.smartnav.smartnav_backend.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartnav.smartnav_backend.dto.WeatherResponse;

@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public WeatherResponse getWeather(
            double lat,
            double lon,
            String lang) {

        String url =
                "https://api.openweathermap.org/data/2.5/weather"
                        + "?lat=" + lat
                        + "&lon=" + lon
                        + "&units=metric"
                        + "&lang=" + lang
                        + "&appid=" + apiKey;

        RestTemplate restTemplate =
                new RestTemplate();

        String response =
                restTemplate.getForObject(
                        url,
                        String.class);

        try {

            ObjectMapper mapper =
                    new ObjectMapper();

            JsonNode root =
                    mapper.readTree(response);

            String location =
                    getCurrentLocation(
                            lat,
                            lon);

            double temperature =
                    root.get("main")
                            .get("temp")
                            .asDouble();

            String weather =
                    root.get("weather")
                            .get(0)
                            .get("description")
                            .asText();

            String currentTime =
                    LocalTime.now()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "hh:mm a"));

            return new WeatherResponse(
                    location,
                    temperature,
                    weather,
                    currentTime);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to parse weather data",
                    e);
        }
    }

    private String getCurrentLocation(
            double lat,
            double lon) {

        try {

            String url =
                    "https://nominatim.openstreetmap.org/reverse"
                            + "?format=json"
                            + "&lat=" + lat
                            + "&lon=" + lon;

            RestTemplate restTemplate =
                    new RestTemplate();

            HttpHeaders headers =
                    new HttpHeaders();

            headers.set(
                    "User-Agent",
                    "SmartNav/1.0");

            HttpEntity<String> entity =
                    new HttpEntity<>(headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            String.class);

            ObjectMapper mapper =
                    new ObjectMapper();

            JsonNode root =
                    mapper.readTree(
                            response.getBody());

            JsonNode address =
                    root.get("address");

            if (address != null) {

                if (address.has("village")) {
                    return address.get("village").asText();
                }

                if (address.has("town")) {
                    return address.get("town").asText();
                }

                if (address.has("suburb")) {
                    return address.get("suburb").asText();
                }

                if (address.has("city")) {
                    return address.get("city").asText();
                }
            }

            return "Unknown";

        } catch (Exception e) {

            return "Unknown";
        }
    }
}