package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.Alert;
import com.smartnav.smartnav_backend.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
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
        String message,
        String priority
) {

    Optional<Alert> existingAlert =
            alertRepository
                    .findTopByVehicleNumberAndAlertTypeOrderByCreatedTimeDesc(
                            vehicleNumber,
                            alertType
                    );

    if (existingAlert.isPresent()) {

        Alert lastAlert = existingAlert.get();

        if (lastAlert.getMessage().equals(message)) {

            System.out.println(
                    "Duplicate alert ignored"
            );

            return lastAlert;
        }
    }

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

    alert.setIsRead(false);

    alert.setPriority(priority);

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

    public long getUnreadCount(String vehicleNumber){

        return alertRepository
            .countByVehicleNumberAndIsReadFalse(
                    vehicleNumber
            );
    }

    public void markAsRead(Long id){
        Alert alert = alertRepository.findById(id).orElseThrow();

        alert.setIsRead(true);

        alertRepository.save(alert);
    }
}