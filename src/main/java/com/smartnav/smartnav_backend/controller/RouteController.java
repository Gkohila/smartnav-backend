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
}