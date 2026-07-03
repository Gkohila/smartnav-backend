package com.smartnav.smartnav_backend.dto;

import lombok.Data;

@Data
public class FavoriteRouteRequest {

    private Long userId;

    private String vehicleNumber;

    private String busName;

    private String source;

    private String destination;

    private String transportMode;

    private Double fare;

    private Integer duration;
}