package com.smartnav.smartnav_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.smartnav.smartnav_backend.entity.FavoriteRoute;

public interface FavoriteRouteRepository
        extends JpaRepository<FavoriteRoute, Long> {

    // Get all favorites of a user
    List<FavoriteRoute> findByUserIdOrderByCreatedAtDesc(
            Long userId);

    // Check if already favorite
    boolean existsByUserIdAndVehicleNumberAndSourceAndDestinationAndTransportMode(
            Long userId,
            String vehicleNumber,
            String source,
            String destination,
            String transportMode);

    // Delete all favorites
    @Transactional
    @Modifying
    @Query("DELETE FROM FavoriteRoute f WHERE f.userId = :userId")
    void deleteAllByUserId(
            @Param("userId") Long userId);

    // Remove one favorite
    @Transactional
    @Modifying
    void deleteByUserIdAndVehicleNumberAndSourceAndDestinationAndTransportMode(
            Long userId,
            String vehicleNumber,
            String source,
            String destination,
            String transportMode);
}