package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.Alert;
import com.smartnav.smartnav_backend.service.AlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin("*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(
            AlertService alertService
    ) {
        this.alertService = alertService;
    }

    @GetMapping("/{vehicleNumber}")
    public List<Alert> getAlerts(
            @PathVariable String vehicleNumber
    ) {

        return alertService.getAlerts(
                vehicleNumber
        );
    }
}