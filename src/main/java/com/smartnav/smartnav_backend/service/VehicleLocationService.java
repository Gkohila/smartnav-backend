package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.VehicleLocation;
import com.smartnav.smartnav_backend.repository.VehicleLocationRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class VehicleLocationService {

    private final VehicleLocationRepository repository;
    private final StopService stopService;

    public VehicleLocationService(
            VehicleLocationRepository repository,
            StopService stopService
    ) {
        this.repository = repository;
        this.stopService = stopService;
    }

    public VehicleLocation saveLocation(
            VehicleLocation location
    ) {

        VehicleLocation savedLocation =
                repository.save(location);

        learnStop(savedLocation);

        return savedLocation;
    }

    private void learnStop(
            VehicleLocation currentLocation
    ) {

        if (currentLocation.getSpeed() == null ||
                currentLocation.getSpeed() > 2) {

            return;
        }

        List<VehicleLocation> locations =
                repository.findTop10ByVehicleNumberOrderByIdDesc(
                        currentLocation.getVehicleNumber()
                );

        if (locations.size() < 2) {
            return;
        }

        VehicleLocation oldest =
                locations.get(locations.size() - 1);

        VehicleLocation newest =
                locations.get(0);

        long durationSeconds =
                Duration.between(
                        oldest.getTimestamp(),
                        newest.getTimestamp()
                ).getSeconds();

        System.out.println(
        "Duration Seconds = "
                + durationSeconds
);

        System.out.println(
        "Creating stop for "
                + currentLocation.getVehicleNumber()
);

        System.out.println(
        "createLearnedStop called : "
                + durationSeconds
);

        stopService.createLearnedStop(
                
                currentLocation.getVehicleNumber(),
                currentLocation.getVehicleType(),
                currentLocation.getLatitude(),
                currentLocation.getLongitude(),
                durationSeconds
        );
    }

    public List<VehicleLocation> getAllLocations() {
        return repository.findAll();
    }

    public List<VehicleLocation> getByVehicleNumber(
            String vehicleNumber
    ) {
        return repository.findByVehicleNumber(
                vehicleNumber
        );
    }
}