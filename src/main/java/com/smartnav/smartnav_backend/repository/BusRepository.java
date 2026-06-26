package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}