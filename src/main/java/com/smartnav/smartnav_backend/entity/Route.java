package com.smartnav.smartnav_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;

    private String source;

    private String destination;
}