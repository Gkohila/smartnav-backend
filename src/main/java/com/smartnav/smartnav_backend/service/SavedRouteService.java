package com.smartnav.smartnav_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartnav.smartnav_backend.dto.SavedRouteRequest;
import com.smartnav.smartnav_backend.entity.SavedRoute;
import com.smartnav.smartnav_backend.repository.SavedRouteRepository;

@Service
public class SavedRouteService {

    private final SavedRouteRepository repository;

    public SavedRouteService(SavedRouteRepository repository) {
        this.repository = repository;
    }

    public SavedRoute saveRoute(SavedRouteRequest request) {

        SavedRoute route = new SavedRoute();

        route.setUserId(request.getUserId());
        route.setVehicleNumber(request.getVehicleNumber());
        route.setBusName(request.getBusName());
        route.setSource(request.getSource().trim());
        route.setDestination(request.getDestination().trim());
        route.setDepartureTime(request.getDepartureTime());
        route.setArrivalTime(request.getArrivalTime());
        route.setDuration(request.getDuration());
        route.setFare(request.getFare());
        route.setTransportMode(request.getTransportMode().trim());
        route.setCreatedAt(LocalDateTime.now());

        return repository.save(route);
    }

    public List<SavedRoute> getSavedRoutes(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public boolean isRouteSaved(
            Long userId,
            String source,
            String destination,
            String transportMode) {

        return repository.existsByUserIdAndSourceAndDestinationAndTransportMode(
                userId,
                source.trim(),
                destination.trim(),
                transportMode.trim());
    }

    public void deleteRoute(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllRoutes(Long userId) {
        repository.deleteAllByUserId(userId);
    }
}