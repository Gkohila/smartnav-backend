package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.Bus;
import com.smartnav.smartnav_backend.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
}