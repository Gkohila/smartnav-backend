package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.Alert;
import com.smartnav.smartnav_backend.service.AlertService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/{vehicleNumber}/unread-count")
    public long getUnreadCount(
            @PathVariable String vehicleNumber
    ) {

        return alertService.getUnreadCount(
                vehicleNumber
        );
    }

    @PutMapping("/{id}/read")
    public void markAsRead(
            @PathVariable Long id
    ) {

        alertService.markAsRead(id);
    }

    @DeleteMapping("/{vehicleNumber}")
public ResponseEntity<String> deleteAllAlerts(
        @PathVariable String vehicleNumber) {

    alertService.deleteAllAlerts(vehicleNumber);

    return ResponseEntity.ok("Alerts deleted successfully");
}
}