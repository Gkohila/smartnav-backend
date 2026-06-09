package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository
        extends JpaRepository<Alert, Long> {

    List<Alert> findByVehicleNumberOrderByCreatedTimeDesc(
            String vehicleNumber
    );
}