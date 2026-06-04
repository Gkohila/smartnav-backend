package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}