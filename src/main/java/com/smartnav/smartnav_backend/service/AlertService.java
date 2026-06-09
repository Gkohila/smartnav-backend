package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.Alert;
import com.smartnav.smartnav_backend.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(
            AlertRepository alertRepository
    ) {
        this.alertRepository = alertRepository;
    }

    public Alert createAlert(
            String vehicleNumber,
            String alertType,
            String message
    ) {

        Alert alert = new Alert();

        alert.setVehicleNumber(
                vehicleNumber
        );

        alert.setAlertType(
                alertType
        );

        alert.setMessage(
                message
        );

        alert.setCreatedTime(
                LocalDateTime.now()
        );

        alert.setIsRead(
                false
        );

        return alertRepository.save(
                alert
        );
    }

    public List<Alert> getAlerts(
            String vehicleNumber
    ) {

        return alertRepository
                .findByVehicleNumberOrderByCreatedTimeDesc(
                        vehicleNumber
                );
    }
}