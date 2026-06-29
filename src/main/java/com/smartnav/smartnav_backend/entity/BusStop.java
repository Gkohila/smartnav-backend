package com.smartnav.smartnav_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;

    private String stopName;

    private String arrivalTime;

    private Integer stopOrder;
}