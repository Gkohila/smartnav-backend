package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.Bus;
import com.smartnav.smartnav_backend.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    public Bus createBus(@RequestBody Bus bus) {
        return busService.saveBus(bus);
    }

    @GetMapping
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }
}