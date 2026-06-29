package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.BusStop;
import com.smartnav.smartnav_backend.repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusStopService {

    @Autowired
    private BusStopRepository busStopRepository;

    public List<BusStop> getStopsByBusNumber(String busNumber) {

        return busStopRepository
                .findByBusNumberOrderByStopOrder(busNumber);

    }
}