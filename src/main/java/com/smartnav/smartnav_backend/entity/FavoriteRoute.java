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
@Table(name = "favorite_route")
public class FavoriteRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Logged in user
    private Long userId;

    // Vehicle Details
    private String vehicleNumber;

    private String busName;

    // Route Details
    private String source;

    private String destination;

    // Transport Details
    private String transportMode;

    // Fare
    private Double fare;

    // Approximate Duration (Minutes)
    private Integer duration;

    // Created Time
    private LocalDateTime createdAt;
}