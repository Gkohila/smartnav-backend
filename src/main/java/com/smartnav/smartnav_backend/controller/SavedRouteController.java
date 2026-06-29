package com.smartnav.smartnav_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartnav.smartnav_backend.dto.SavedRouteRequest;
import com.smartnav.smartnav_backend.entity.SavedRoute;
import com.smartnav.smartnav_backend.service.SavedRouteService;

@RestController
@RequestMapping("/api/saved-routes")
@CrossOrigin(origins = "*")
public class SavedRouteController {

    private final SavedRouteService service;

    public SavedRouteController(
            SavedRouteService service) {

        this.service = service;
    }

    @PostMapping
    public SavedRoute saveRoute(
            @RequestBody SavedRouteRequest request) {

        return service.saveRoute(request);
    }

    @GetMapping("/{userId}")
    public List<SavedRoute> getSavedRoutes(
            @PathVariable Long userId) {

        return service.getSavedRoutes(userId);
    }

    @GetMapping("/exists")
public boolean isRouteSaved(
        @RequestParam Long userId,
        @RequestParam String vehicleNumber) {

    return service.isRouteSaved(
            userId,
            vehicleNumber);
}

    @DeleteMapping("/{id}")
    public void deleteRoute(
            @PathVariable Long id) {

        service.deleteRoute(id);
    }
}