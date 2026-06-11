package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.VehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleLocationRepository
        extends JpaRepository<VehicleLocation, Long> {

    List<VehicleLocation> findByVehicleNumber(
            String vehicleNumber
    );

    List<VehicleLocation> findTop10ByVehicleNumberOrderByIdDesc(
            String vehicleNumber
    );

    VehicleLocation findTopByVehicleNumberOrderByIdDesc(
        String vehicleNumber
    );
}