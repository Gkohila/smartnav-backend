package com.smartnav.smartnav_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "bus_name")
    private String busName;

    private String source;

    private String destination;

    @Column(name = "departure_time")
    private String departureTime;

    @Column(name = "arrival_time")
    private String arrivalTime;

    private String duration;

    private Double fare;

    @Column(name = "transport_mode")
    private String transportMode;

    @Column(name = "transfer_count")
    private Integer transferCount;

    @Column(name = "walking_distance")
    private Double walkingDistance;

    private String status;
}