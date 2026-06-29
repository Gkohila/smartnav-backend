package com.smartnav.smartnav_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;

    private String trainName;

    private String source;

    private String destination;

    private String status;
}