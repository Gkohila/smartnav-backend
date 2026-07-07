package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.Bus;
import com.smartnav.smartnav_backend.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartnav.smartnav_backend.dto.RouteSearchResponse;
import com.smartnav.smartnav_backend.repository.BusStopRepository;
import java.util.ArrayList;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusStopRepository busStopRepository;

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public List<RouteSearchResponse> searchRoutes(
        String source,
        String destination) {

    List<RouteSearchResponse> results = new ArrayList<>();

    // Direct Route Search
    List<Bus> directBuses =
            busRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(
                    source,
                    destination);

    for (Bus bus : directBuses) {

        RouteSearchResponse response =
                new RouteSearchResponse();

        response.setRouteType("DIRECT");

        response.setBusNumbers(
                List.of(bus.getBusNumber()));

        response.setBusNames(
                List.of(bus.getBusName()));

        response.setTransferCount(0);

        response.setTotalFare(
                bus.getFare());

        response.setTotalDuration(
                bus.getDuration());

        results.add(response);
    }

    return results;
}
}