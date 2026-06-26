package com.smartnav.smartnav_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartnav.smartnav_backend.entity.Route;
import com.smartnav.smartnav_backend.service.RouteService;

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
    public Route addRoute(@RequestBody Route route) {
        return routeService.saveRoute(route);
    }

    @GetMapping("/search")
    public List<Route> searchRoutes(
            @RequestParam String source,
            @RequestParam String destination) {

        return routeService.searchRoutes(source, destination);
    }

    // GET Route By Id
    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id);
    }

    // UPDATE Route
    @PutMapping("/{id}")
    public Route updateRoute(
            @PathVariable Long id,
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