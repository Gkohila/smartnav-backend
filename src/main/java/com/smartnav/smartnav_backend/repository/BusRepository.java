package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {

    List<Bus> findBySourceIgnoreCaseAndDestinationIgnoreCase(
            String source,
            String destination
    );

    List<Bus> findBySourceIgnoreCase(String source);

    List<Bus> findByDestinationIgnoreCase(String destination);
}