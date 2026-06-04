package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.Route;
import com.smartnav.smartnav_backend.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
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