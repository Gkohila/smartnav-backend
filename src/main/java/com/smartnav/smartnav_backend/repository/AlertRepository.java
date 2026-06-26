package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlertRepository
        extends JpaRepository<Alert, Long> {

    List<Alert> findByVehicleNumberOrderByCreatedTimeDesc(
            String vehicleNumber
    );

    Optional<Alert> findTopByVehicleNumberAndAlertTypeOrderByCreatedTimeDesc(
        String vehicleNumber,
        String alertType
    );

    long countByVehicleNumberAndIsReadFalse(
        String vehicleNumber
    );
}