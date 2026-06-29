package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteStopRepository
        extends JpaRepository<RouteStop, Long> {

    List<RouteStop> findByRouteIdOrderByStopOrder(
            Long routeId
    );
}