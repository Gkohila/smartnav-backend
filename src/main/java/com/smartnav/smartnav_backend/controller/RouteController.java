package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.Route;
import com.smartnav.smartnav_backend.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin("*")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @PostMapping
    public Route addRoute(
            @RequestBody Route route
    ) {
        return routeService.saveRoute(route);
    }

    @GetMapping("/search")
    public List<Route> searchRoutes(
            @RequestParam String source,
            @RequestParam String destination
    ) {
        return routeService.searchRoutes(
                source,
                destination
        );
    }
}