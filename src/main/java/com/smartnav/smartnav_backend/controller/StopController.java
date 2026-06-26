package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.Stop;
import com.smartnav.smartnav_backend.service.StopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stops")
@CrossOrigin("*")
public class StopController {

    private final StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @PostMapping
    public Stop addStop(@RequestBody Stop stop) {
        return stopService.saveStop(stop);
    }

    @GetMapping
    public List<Stop> getAllStops() {
        return stopService.getAllStops();
    }
}