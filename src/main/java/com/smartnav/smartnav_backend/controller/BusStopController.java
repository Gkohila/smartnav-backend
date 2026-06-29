package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.BusStop;
import com.smartnav.smartnav_backend.service.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus-stops")
@CrossOrigin(origins = "*")
public class BusStopController {

    @Autowired
    private BusStopService busStopService;

    @GetMapping("/{busNumber}")
    public List<BusStop> getStops(
            @PathVariable String busNumber) {

        return busStopService
                .getStopsByBusNumber(busNumber);

    }
}