package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.*;
import com.smartnav.smartnav_backend.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteProgressService {

    private final VehicleRouteRepository vehicleRouteRepository;
    private final RouteStopRepository routeStopRepository;
    private final VehicleLocationRepository vehicleLocationRepository;
    private final AlertService alertService;

    public RouteProgressService(
            VehicleRouteRepository vehicleRouteRepository,
            RouteStopRepository routeStopRepository,
            VehicleLocationRepository vehicleLocationRepository,
            AlertService alertService
    ) {
        this.vehicleRouteRepository = vehicleRouteRepository;
        this.routeStopRepository = routeStopRepository;
        this.vehicleLocationRepository = vehicleLocationRepository;
        this.alertService = alertService;
    }

    public RouteProgress getProgress(
            String vehicleNumber
    ) {
        System.out.println("Vehicle = " + vehicleNumber);

        RouteProgress progress =
                new RouteProgress();

        progress.setVehicleNumber(
                vehicleNumber
        );

        Optional<VehicleRoute> vehicleRoute =
                vehicleRouteRepository.findByVehicleNumber(
                        vehicleNumber
                );

        System.out.println("Vehicle Route Found = " + vehicleRoute.isPresent());

        if (vehicleRoute.isEmpty()) {
            return progress;
        }

        Long routeId =
                vehicleRoute.get().getRouteId();
        System.out.println("Route ID = " + routeId);

        List<RouteStop> routeStops =
                routeStopRepository
                        .findByRouteIdOrderByStopOrder(
                                routeId
                        );
        System.out.println("Route Stops = " + routeStops.size());

        List<VehicleLocation> locations =
                vehicleLocationRepository
                        .findTop10ByVehicleNumberOrderByIdDesc(
                                vehicleNumber
                        );

        System.out.println("Locations = " + locations.size());

        if (locations.isEmpty()
                || routeStops.isEmpty()) {
            return progress;
        }

        VehicleLocation currentLocation =
                locations.get(0);

        RouteStop nearestStop = null;
        double minDistance = Double.MAX_VALUE;

        for (RouteStop stop : routeStops) {

            double distance =
                    calculateDistanceMeters(
                            currentLocation.getLatitude(),
                            currentLocation.getLongitude(),
                            stop.getLatitude(),
                            stop.getLongitude()
                    );

            if (distance < minDistance) {

                minDistance = distance;
                nearestStop = stop;
            }
        }

        if (nearestStop == null) {
            return progress;
        }

        /*
        * Route Deviation Detection
        * If the distance between the current location and the nearest stop is greater than 1000 meters, set the route deviation to true
        * If the distance between the current location and the nearest stop is less than 1000 meters, set the route deviation to false
        */

        progress.setDeviationDistanceMeters(minDistance);

        if (minDistance > 1000) {
            progress.setRouteDeviation(true);

            System.out.println("ROUTE DEVIATION DETECTION");

            alertService.createAlert(
                vehicleNumber,
                "ROUTE_DEVIATION",
                "Bus "
                    + vehicleNumber
                    + " deviated from assigned route", 
                "HIGH"
            );
        } else {
            progress.setRouteDeviation(false);
        }

        progress.setCurrentStop(
                nearestStop.getStopName()
        );

        int currentOrder =
                nearestStop.getStopOrder();

        for (RouteStop stop : routeStops) {

            if (stop.getStopOrder()
                    == currentOrder + 1) {

                progress.setNextStop(
                        stop.getStopName()
                );

                break;
            }
        }

        int totalStops =
                routeStops.size();

        int remainingStops =
                totalStops - currentOrder;

        progress.setStopsRemaining(
                remainingStops
        );

        double percentage =
                ((double) currentOrder
                        / totalStops) * 100;

        progress.setProgressPercentage(
                percentage
        );

        /*
        * ETA Prediction V1
        * Assume 2 minutes per remaining stop
        */

        int etaMinutes = remainingStops * 2;

        progress.setEtaMinutes(etaMinutes);

        /*
        * Approaching Destination Alerts
        */

        if (etaMinutes <= 15 && etaMinutes > 10) {

                alertService.createAlert(
                        vehicleNumber,
                        "APPROACHING_DESTINATION",
                        "Bus "
                        + vehicleNumber
                        + " will reach destination in 15 minutes", 
                        "MEDIUM"
                );
        }

        if (etaMinutes <= 10 && etaMinutes > 5) {

                alertService.createAlert(
                        vehicleNumber,
                        "APPROACHING_DESTINATION",
                        "Bus "
                        + vehicleNumber
                        + " will reach destination in 10 minutes", 
                        "MEDIUM"
                );
        }

        if (etaMinutes <= 5 && etaMinutes > 0) {

                alertService.createAlert(
                        vehicleNumber,
                        "APPROACHING_DESTINATION",
                        "Bus "
                        + vehicleNumber
                        + " will reach destination in 5 minutes", 
                        "HIGH"
                );
        }

        /*
        * Destination Reached Alert
        */

        progress.setDestinationStop(
                routeStops.get(totalStops - 1)
                        .getStopName()
        );

        if (remainingStops == 0) {

                progress.setDestinationReached(true);

                alertService.createAlert(
                        vehicleNumber,
                        "DESTINATION_REACHED",
                        "Bus "
                        + vehicleNumber
                        + " reached "
                        + progress.getDestinationStop(), 
                        "HIGH"
                );

        } else {

            progress.setDestinationReached(false);
        }

        System.out.println("Nearest Stop = " +nearestStop.getStopName());

        return progress;
    }

    private double calculateDistanceMeters(
            Double lat1,
            Double lon1,
            Double lat2,
            Double lon2
    ) {

        final int EARTH_RADIUS = 6371000;

        double latDistance =
                Math.toRadians(
                        lat2 - lat1
                );

        double lonDistance =
                Math.toRadians(
                        lon2 - lon1
                );

        double a =
                Math.sin(
                        latDistance / 2
                ) *
                Math.sin(
                        latDistance / 2
                )
                +
                Math.cos(
                        Math.toRadians(lat1)
                )
                *
                Math.cos(
                        Math.toRadians(lat2)
                )
                *
                Math.sin(
                        lonDistance / 2
                )
                *
                Math.sin(
                        lonDistance / 2
                );

        double c =
                2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a)
                );

        return EARTH_RADIUS * c;
    }
}