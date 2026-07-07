package com.smartnav.smartnav_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class RouteSearchResponse {

    private String routeType;

    private List<String> busNumbers;

    private List<String> busNames;

    private List<String> stops;

    private String transferStop;

    private Integer transferCount;

    private Double totalFare;

    private String totalDuration;
}