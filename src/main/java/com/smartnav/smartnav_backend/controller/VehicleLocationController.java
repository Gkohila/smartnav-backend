package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.VehicleLocation;
import com.smartnav.smartnav_backend.service.VehicleLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin("*")
public class VehicleLocationController {

    private final VehicleLocationService service;

    public VehicleLocationController(
            VehicleLocationService service
    ) {
        this.service = service;
    }

    @PostMapping
    public VehicleLocation saveLocation(
            @RequestBody VehicleLocation location
    ) {
        return service.saveLocation(location);
    }

    @GetMapping
    public List<VehicleLocation> getAllLocations() {
        return service.getAllLocations();
    }

    @GetMapping("/{vehicleNumber}")
    public List<VehicleLocation> getVehicleLocations(
            @PathVariable String vehicleNumber
    ) {
        return service.getByVehicleNumber(
                vehicleNumber
        );
    }
}