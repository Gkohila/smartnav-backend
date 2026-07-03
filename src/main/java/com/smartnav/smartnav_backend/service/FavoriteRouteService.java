package com.smartnav.smartnav_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartnav.smartnav_backend.dto.FavoriteRouteRequest;
import com.smartnav.smartnav_backend.entity.FavoriteRoute;
import com.smartnav.smartnav_backend.repository.FavoriteRouteRepository;

@Service
public class FavoriteRouteService {

    private final FavoriteRouteRepository repository;

    public FavoriteRouteService(
            FavoriteRouteRepository repository) {

        this.repository = repository;
    }

    // Save Favorite
    public FavoriteRoute saveFavorite(
            FavoriteRouteRequest request) {

        FavoriteRoute favorite = new FavoriteRoute();

        favorite.setUserId(request.getUserId());
        favorite.setVehicleNumber(
                request.getVehicleNumber());
        favorite.setBusName(
                request.getBusName());
        favorite.setSource(
                request.getSource().trim());
        favorite.setDestination(
                request.getDestination().trim());
        favorite.setTransportMode(
                request.getTransportMode().trim());
        favorite.setFare(
                request.getFare());
        favorite.setDuration(
                request.getDuration());

        favorite.setCreatedAt(
                LocalDateTime.now());

        return repository.save(favorite);
    }

    // Get Favorites
    public List<FavoriteRoute> getFavorites(
            Long userId) {

        return repository
                .findByUserIdOrderByCreatedAtDesc(
                        userId);
    }

    // Check Favorite
    public boolean isFavorite(
            Long userId,
            String vehicleNumber,
            String source,
            String destination,
            String transportMode) {

        return repository
                .existsByUserIdAndVehicleNumberAndSourceAndDestinationAndTransportMode(
                        userId,
                        vehicleNumber.trim(),
                        source.trim(),
                        destination.trim(),
                        transportMode.trim());
    }

    // Remove Favorite
    public void removeFavorite(
            Long userId,
            String vehicleNumber,
            String source,
            String destination,
            String transportMode) {

        repository
                .deleteByUserIdAndVehicleNumberAndSourceAndDestinationAndTransportMode(
                        userId,
                        vehicleNumber.trim(),
                        source.trim(),
                        destination.trim(),
                        transportMode.trim());
    }

    // Delete All Favorites
    public void deleteAllFavorites(
            Long userId) {

        repository.deleteAllByUserId(userId);
    }
}