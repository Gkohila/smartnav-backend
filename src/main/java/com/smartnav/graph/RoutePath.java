package com.smartnav.smartnav_backend.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoutePath {

    private List<GraphEdge> edges;

}