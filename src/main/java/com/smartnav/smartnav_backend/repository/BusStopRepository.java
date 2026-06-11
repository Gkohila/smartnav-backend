package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {

    List<BusStop> findByBusNumberOrderByStopOrder(String busNumber);

}