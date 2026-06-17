package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.Route;
import com.smartnav.smartnav_backend.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return routeService.saveRoute(route);
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    // GET Route By Id
    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id);
    }

    // UPDATE Route
    @PutMapping("/{id}")
    public Route updateRoute(@PathVariable Long id,
                             @RequestBody Route route) {
        return routeService.updateRoute(id, route);
    }

    // DELETE Route
    @DeleteMapping("/{id}")
    public String deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "Route Deleted Successfully";
    }
}