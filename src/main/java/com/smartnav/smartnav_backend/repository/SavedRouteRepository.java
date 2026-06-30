package com.smartnav.smartnav_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.smartnav.smartnav_backend.entity.SavedRoute;

public interface SavedRouteRepository
        extends JpaRepository<SavedRoute, Long> {

    List<SavedRoute> findByUserIdOrderByCreatedAtDesc(
            Long userId);

    boolean existsByUserIdAndSourceAndDestinationAndTransportMode(
            Long userId,
            String source,
            String destination,
            String transportMode);

    @Transactional
    @Modifying
    @Query("DELETE FROM SavedRoute s WHERE s.userId = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);
}