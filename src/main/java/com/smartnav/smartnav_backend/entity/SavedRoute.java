package com.smartnav.smartnav_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "saved_route")
public class SavedRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String vehicleNumber;

    private String busName;

    private String source;

    private String destination;

    private String departureTime;

    private String arrivalTime;

    private Integer duration;

    private Double fare;

    private String transportMode;

    private LocalDateTime createdAt;
}