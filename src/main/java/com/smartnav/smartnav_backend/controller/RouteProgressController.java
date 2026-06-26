package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.RouteProgress;
import com.smartnav.smartnav_backend.service.RouteProgressService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin("*")
public class RouteProgressController {

    private final RouteProgressService routeProgressService;

    public RouteProgressController(
            RouteProgressService routeProgressService
    ) {
        this.routeProgressService = routeProgressService;
    }

    @GetMapping("/test")
    public String test() {
        return "WORKING";
    }

    @GetMapping("/{vehicleNumber}")
    public RouteProgress getProgress(
            @PathVariable String vehicleNumber
    ) {
        return routeProgressService.getProgress(
                vehicleNumber
        );
    }
}