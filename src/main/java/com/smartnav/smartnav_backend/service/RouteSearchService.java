package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.repository.BusRepository;
import com.smartnav.smartnav_backend.repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartnav.smartnav_backend.entity.BusStop;
import com.smartnav.smartnav_backend.graph.GraphEdge;
import com.smartnav.smartnav_backend.graph.GraphNode;
import com.smartnav.smartnav_backend.entity.Bus;
import com.smartnav.smartnav_backend.graph.RoutePath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

import jakarta.annotation.PostConstruct;

@Service
public class RouteSearchService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusStopRepository busStopRepository;

    private final Map<String, GraphNode> graph = new HashMap<>();

    Queue<String> queue = new LinkedList<>();

    Set<String> visited = new HashSet<>();

    Map<String, GraphEdge> parent = new HashMap<>();

    @PostConstruct
public void initializeGraph() {

    buildGraph();
}

    private GraphNode getOrCreateNode(String stopName) {

    return graph.computeIfAbsent(
            stopName,
            GraphNode::new
    );
}

private void buildGraph() {

    graph.clear();

    List<BusStop> busStops =
            busStopRepository.findAllByOrderByBusNumberAscStopOrderAsc();

    Map<String, Bus> busMap = new HashMap<>();

for (Bus bus : busRepository.findAll()) {
    busMap.put(bus.getBusNumber(), bus);
}

    for (int i = 0; i < busStops.size() - 1; i++) {

        BusStop current = busStops.get(i);
        BusStop next = busStops.get(i + 1);

        if (!current.getBusNumber().equals(next.getBusNumber())) {
            continue;
        }

        GraphNode fromNode =
                getOrCreateNode(current.getStopName());

        getOrCreateNode(next.getStopName());

        Bus bus = busMap.get(current.getBusNumber());

        fromNode.getEdges().add(

                new GraphEdge(
                        current.getBusNumber(),
                        current.getStopName(),
                        next.getStopName(),
                        current.getStopOrder()
                )

        );

    }
    printGraph();

}

private void printGraph() {

    System.out.println("\n========== SMARTNAV GRAPH ==========");

    for (GraphNode node : graph.values()) {

        for (GraphEdge edge : node.getEdges()) {

            System.out.println(
                    edge.getFromStop()
                            + " --("
                            + edge.getBusNumber()
                            + ")--> "
                            + edge.getToStop()
            );
        }
    }

    System.out.println("====================================\n");
}

private RoutePath buildRoute(
        Map<String, GraphEdge> parent,
        String destination
) {

    List<GraphEdge> route = new ArrayList<>();

    String current = destination;

    while (parent.containsKey(current)) {

        GraphEdge edge = parent.get(current);

        route.add(edge);

        current = edge.getFromStop();
    }

    Collections.reverse(route);

    return new RoutePath(route);

}

public RoutePath findShortestPath(
        String source,
        String destination
) {

    buildGraph();

    Queue<String> queue = new LinkedList<>();

    Set<String> visited = new HashSet<>();

    queue.offer(source);

    visited.add(source);

    System.out.println("Searching route from "
            + source + " to " + destination);

    while (!queue.isEmpty()) {

        String current = queue.poll();

        System.out.println("Visiting : " + current);

        if (current.equalsIgnoreCase(destination)) {

            System.out.println("Destination Found!");

            return buildRoute(parent, destination);
        }

        GraphNode node = graph.get(current);

        if (node == null) {
            continue;
        }

        for (GraphEdge edge : node.getEdges()) {

            if (!visited.contains(edge.getToStop())) {

                visited.add(edge.getToStop());

                parent.put(edge.getToStop(), edge);

                queue.offer(edge.getToStop());

            }

        }

    }

    System.out.println("No Route Found");

    return null;
}

}