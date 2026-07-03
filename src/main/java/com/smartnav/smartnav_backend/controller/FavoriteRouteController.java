package com.smartnav.smartnav_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartnav.smartnav_backend.dto.FavoriteRouteRequest;
import com.smartnav.smartnav_backend.entity.FavoriteRoute;
import com.smartnav.smartnav_backend.service.FavoriteRouteService;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
public class FavoriteRouteController {

    private final FavoriteRouteService service;

    public FavoriteRouteController(
            FavoriteRouteService service) {
        this.service = service;
    }

    // Add Favorite
    @PostMapping
    public FavoriteRoute addFavorite(
            @RequestBody FavoriteRouteRequest request) {

        return service.saveFavorite(request);
    }

    // Get Favorites
    @GetMapping("/{userId}")
    public List<FavoriteRoute> getFavorites(
            @PathVariable Long userId) {

        return service.getFavorites(userId);
    }

    // Check Favorite
    @GetMapping("/exists")
    public boolean isFavorite(

            @RequestParam Long userId,

            @RequestParam String vehicleNumber,

            @RequestParam String source,

            @RequestParam String destination,

            @RequestParam String transportMode) {

        return service.isFavorite(
                userId,
                vehicleNumber,
                source,
                destination,
                transportMode);
    }

    // Remove Favorite
    @DeleteMapping
    public Map<String, String> removeFavorite(

            @RequestParam Long userId,

            @RequestParam String vehicleNumber,

            @RequestParam String source,

            @RequestParam String destination,

            @RequestParam String transportMode) {

        service.removeFavorite(
                userId,
                vehicleNumber,
                source,
                destination,
                transportMode);

        Map<String, String> response =
                new HashMap<>();

        response.put(
                "message",
                "Favorite removed successfully");

        return response;
    }

    // Delete All Favorites
    @DeleteMapping("/user/{userId}")
    public Map<String, String> deleteAllFavorites(
            @PathVariable Long userId) {

        service.deleteAllFavorites(userId);

        Map<String, String> response =
                new HashMap<>();

        response.put(
                "message",
                "All favorites removed successfully");

        return response;
    }
}