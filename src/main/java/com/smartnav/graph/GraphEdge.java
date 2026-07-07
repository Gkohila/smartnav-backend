package com.smartnav.smartnav_backend.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraphEdge {

    private String busNumber;

    private String busName;

    private Double fare;

    private String duration;

    private String transportMode;

    private String fromStop;

    private String toStop;

    private Integer stopOrder;
}