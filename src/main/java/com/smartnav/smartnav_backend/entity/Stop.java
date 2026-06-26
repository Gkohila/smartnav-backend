package com.smartnav.smartnav_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stops")
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stopName;

    private Double latitude;

    private Double longitude;

    private String stopType;

    private Integer visitCount;

    private Double averageStopTime;

    private Long stopDuration;

    private String vehicleNumber;

    private String vehicleType;

    // Learning counters
    private Integer miniStopCount;

    private Integer majorStopCount;

    private Integer mainStopCount;

    private Boolean delayDetected;

    public Stop() {
    }

    public Long getId() {
        return id;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStopType() {
        return stopType;
    }

    public void setStopType(String stopType) {
        this.stopType = stopType;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Double getAverageStopTime() {
        return averageStopTime;
    }

    public void setAverageStopTime(Double averageStopTime) {
        this.averageStopTime = averageStopTime;
    }

    public Long getStopDuration() {
        return stopDuration;
    }

    public void setStopDuration(Long stopDuration) {
        this.stopDuration = stopDuration;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getMiniStopCount() {
        return miniStopCount;
    }

    public void setMiniStopCount(Integer miniStopCount) {
        this.miniStopCount = miniStopCount;
    }

    public Integer getMajorStopCount() {
        return majorStopCount;
    }

    public void setMajorStopCount(Integer majorStopCount) {
        this.majorStopCount = majorStopCount;
    }

    public Integer getMainStopCount() {
        return mainStopCount;
    }

    public void setMainStopCount(Integer mainStopCount) {
        this.mainStopCount = mainStopCount;
    }

    public Boolean getDelayDetected() {
        return delayDetected;
    }

    public void setDelayDetected(Boolean delayDetected) {
        this.delayDetected = delayDetected;
    }
}