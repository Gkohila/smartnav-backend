package com.smartnav.smartnav_backend.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GraphNode {

    private String stopName;

    private List<GraphEdge> edges = new ArrayList<>();

    public GraphNode(String stopName) {
        this.stopName = stopName;
    }
}