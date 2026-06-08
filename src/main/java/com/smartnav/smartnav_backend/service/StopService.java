package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.Stop;
import com.smartnav.smartnav_backend.repository.StopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopService {

    private static final double STOP_RADIUS_METERS = 10.0;

    private final StopRepository stopRepository;

    public StopService(
            StopRepository stopRepository
    ) {
        this.stopRepository = stopRepository;
    }

    public Stop saveStop(Stop stop) {

        List<Stop> existingStops =
                stopRepository.findByVehicleNumber(
                        stop.getVehicleNumber()
                );

        Stop nearbyStop = null;

        for (Stop existing : existingStops) {

            double distance =
                    calculateDistanceMeters(
                            stop.getLatitude(),
                            stop.getLongitude(),
                            existing.getLatitude(),
                            existing.getLongitude()
                    );

            if (distance <= STOP_RADIUS_METERS) {

                nearbyStop = existing;
                break;
            }
        }

        if (nearbyStop != null) {

            int oldVisits =
                    nearbyStop.getVisitCount() == null
                            ? 0
                            : nearbyStop.getVisitCount();

            int newVisits = oldVisits + 1;

            nearbyStop.setVisitCount(newVisits);

            nearbyStop.setStopDuration(
                    stop.getStopDuration()
            );

            String currentType =
                    nearbyStop.getStopType();

            if (currentType != null &&
                    currentType.startsWith("TEMP")) {

                if (newVisits >= 10) {

                    nearbyStop.setStopType(
                            currentType.replace(
                                    "TEMPORARY_",
                                    "PERMANENT_"
                            )
                    );
                }
            }

            /*
             * Average Stop Time Learning
             */

            Long currentDuration =
                    stop.getStopDuration();

            Double oldAverage =
                    nearbyStop.getAverageStopTime();

            if (oldAverage == null) {

                nearbyStop.setAverageStopTime(
                        currentDuration.doubleValue()
                );

            } else {

                double newAverage =
                        (
                                (oldAverage * oldVisits)
                                        + currentDuration
                        ) / newVisits;

                nearbyStop.setAverageStopTime(
                        newAverage
                );
            }

            /*
             * Delay Detection V1
             */

            Double average =
                    nearbyStop.getAverageStopTime();

            if (average != null &&
                    currentDuration > average * 2) {

                nearbyStop.setDelayDetected(
                        true
                );

            } else {

                nearbyStop.setDelayDetected(
                        false
                );
            }

            return stopRepository.save(
                    nearbyStop
            );
        }

        /*
         * First Visit
         */

        stop.setVisitCount(1);

        stop.setAverageStopTime(
                stop.getStopDuration()
                        .doubleValue()
        );

        stop.setDelayDetected(false);

        return stopRepository.save(stop);
    }

    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    public Stop createLearnedStop(
            String vehicleNumber,
            String vehicleType,
            Double latitude,
            Double longitude,
            long durationSeconds
    ) {

        System.out.println(
                "createLearnedStop called : "
                        + durationSeconds
        );

        Stop stop = new Stop();

        stop.setVehicleNumber(vehicleNumber);
        stop.setVehicleType(vehicleType);
        stop.setLatitude(latitude);
        stop.setLongitude(longitude);
        stop.setStopDuration(durationSeconds);

        if (durationSeconds >= 3 &&
                durationSeconds < 15) {

            stop.setStopType(
                    "TEMPORARY_MINI_STOP"
            );

        } else if (durationSeconds >= 15 &&
                durationSeconds < 30) {

            stop.setStopType(
                    "TEMPORARY_MAJOR_STOP"
            );

        } else if (durationSeconds >= 30) {

            stop.setStopType(
                    "TEMPORARY_MAIN_STOP"
            );

        } else {

            return null;
        }

        return saveStop(stop);
    }

    private double calculateDistanceMeters(
            Double lat1,
            Double lon1,
            Double lat2,
            Double lon2
    ) {

        final int EARTH_RADIUS = 6371000;

        double latDistance =
                Math.toRadians(lat2 - lat1);

        double lonDistance =
                Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(latDistance / 2)
                        * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2)
                        * Math.sin(lonDistance / 2);

        double c =
                2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a)
                );

        return EARTH_RADIUS * c;
    }
}