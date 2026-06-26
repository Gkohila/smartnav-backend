package com.smartnav.smartnav_backend.dto;

import lombok.Data;

@Data
public class SavedRouteRequest {

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
}