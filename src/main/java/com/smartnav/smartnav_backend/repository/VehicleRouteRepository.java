package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.VehicleRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRouteRepository
        extends JpaRepository<VehicleRoute, Long> {

    Optional<VehicleRoute> findByVehicleNumber(
            String vehicleNumber
    );
}