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

    public WeatherResponse getWeather(
            double lat,
            double lon,
            String lang) {

        try {

            System.out.println("LAT = " + lat);
            System.out.println("LON = " + lon);

            String weatherUrl =
                    "https://api.openweathermap.org/data/2.5/weather"
                            + "?lat=" + lat
                            + "&lon=" + lon
                            + "&units=metric"
                            + "&lang=" + lang
                            + "&appid=" + apiKey;

            RestTemplate restTemplate = new RestTemplate();

            String weatherJson =
                    restTemplate.getForObject(
                            weatherUrl,
                            String.class);

            ObjectMapper mapper =
                    new ObjectMapper();

            JsonNode root =
                    mapper.readTree(weatherJson);

                    double temperature =
                    root.path("main")
                            .path("temp")
                            .asDouble();
            
            String weather =
                    root.path("weather")
                            .get(0)
                            .path("description")
                            .asText();
            
            // OpenWeather city name
            String city =
                    root.path("name")
                            .asText();
            
            String currentTime =
                    LocalTime.now().format(
                            DateTimeFormatter.ofPattern(
                                    "hh:mm a"));
            
            // Reverse Geocoding location
            String location =
                    getCurrentLocation(
                            lat,
                            lon);
            
            // Fallback
            if ("Unknown".equals(location) || location.isBlank()) {
                location = city;
            }
            
            return new WeatherResponse(
                    location,
                    temperature,
                    weather,
                    currentTime);
        } catch (Exception e) {

            e.printStackTrace();

            return new WeatherResponse(
                    "Unknown",
                    0,
                    "Unavailable",
                    LocalTime.now().format(
                            DateTimeFormatter.ofPattern(
                                    "hh:mm a")));
        }
    }

    private String getCurrentLocation(
            double lat,
            double lon) {

        try {

            String url =
                    "https://nominatim.openstreetmap.org/reverse"
                            + "?format=jsonv2"
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

            System.out.println("REVERSE JSON:");
            System.out.println(response.getBody());

            ObjectMapper mapper =
                    new ObjectMapper();

            JsonNode root =
                    mapper.readTree(
                            response.getBody());

            JsonNode address =
                    root.path("address");

            String[] fields = {
                    "suburb",
                    "hamlet",
                    "village",
                    "town",
                    "city",
                    "municipality",
                    "neighbourhood",
                    "county",
                    "state_district",
                    "state"
            };

            for (String field : fields) {

                if (address.has(field)) {

                    String place =
                            address.get(field)
                                    .asText();

                    System.out.println(
                            "FOUND LOCATION = " + place);

                    return place;
                }
            }

            if (root.has("display_name")) {

                String display =
                        root.get("display_name")
                                .asText();

                System.out.println(
                        "DISPLAY NAME = "
                                + display);

                return display.split(",")[0];
            }

            return "Unknown";

        } catch (Exception e) {

            e.printStackTrace();

            return "Unknown";
        }
    }
}