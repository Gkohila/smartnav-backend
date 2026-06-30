package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {

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

    @Transactional
    @Modifying
    @Query("DELETE FROM Alert a WHERE a.vehicleNumber = :vehicleNumber")
    void deleteByVehicleNumber(
            @Param("vehicleNumber") String vehicleNumber
    );
}