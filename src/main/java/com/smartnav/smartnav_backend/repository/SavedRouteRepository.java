package com.smartnav.smartnav_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartnav.smartnav_backend.entity.SavedRoute;

public interface SavedRouteRepository
        extends JpaRepository<SavedRoute, Long> {

    List<SavedRoute> findByUserIdOrderByCreatedAtDesc(
            Long userId);
    
    boolean existsByUserIdAndVehicleNumber(
                Long userId,
                String vehicleNumber);
}
