package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.Train;
import com.smartnav.smartnav_backend.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public Train saveTrain(Train train) {
        return trainRepository.save(train);
    }

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }
}