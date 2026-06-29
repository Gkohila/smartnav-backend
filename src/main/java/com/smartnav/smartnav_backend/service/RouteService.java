package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.Route;
import com.smartnav.smartnav_backend.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public List<Route> searchRoutes(
            String source,
            String destination
    ) {
        return routeRepository.findBySourceAndDestination(
                source,
                destination
        );
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElse(null);
    }

    public Route updateRoute(Long id, Route route) {

        Route existingRoute =
                routeRepository.findById(id).orElse(null);

        if (existingRoute != null) {
            existingRoute.setRouteName(route.getRouteName());
            existingRoute.setSource(route.getSource());
            existingRoute.setDestination(route.getDestination());

            return routeRepository.save(existingRoute);
        }

        return null;
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}