package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
}