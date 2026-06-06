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
}