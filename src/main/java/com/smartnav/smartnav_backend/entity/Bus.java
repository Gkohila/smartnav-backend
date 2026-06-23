package com.smartnav.smartnav_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;

    private String busName;

    private String source;

    private String destination;

    private String status;

    private Integer duration;

    private Integer walkingDistance;

    private Integer transferCount;

    private String departureTime;

    private String arrivalTime;

    private String transportMode;

    private Integer fare;

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }
}