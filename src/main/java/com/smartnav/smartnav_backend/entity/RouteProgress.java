package com.smartnav.smartnav_backend.entity;

public class RouteProgress {

    private String vehicleNumber;

    private String currentStop;

    private String nextStop;

    private Integer stopsRemaining;

    private Double progressPercentage;

    private Integer etaMinutes;

    private Boolean routeDeviation;

    private Double deviationDistanceMeters;

    private Boolean destinationReached;

    private String destinationStop;

    public RouteProgress() {
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getCurrentStop() {
        return currentStop;
    }

    public void setCurrentStop(String currentStop) {
        this.currentStop = currentStop;
    }

    public String getNextStop() {
        return nextStop;
    }

    public void setNextStop(String nextStop) {
        this.nextStop = nextStop;
    }

    public Integer getStopsRemaining() {
        return stopsRemaining;
    }

    public void setStopsRemaining(Integer stopsRemaining) {
        this.stopsRemaining = stopsRemaining;
    }

    public Double getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(Double progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public Integer getEtaMinutes() {
        return etaMinutes;
    }

    public void setEtaMinutes(Integer etaMinutes) {
        this.etaMinutes = etaMinutes;
    }

    public Boolean getRouteDeviation() {
        return routeDeviation;
    }

    public void setRouteDeviation(Boolean routeDeviation) {
        this.routeDeviation = routeDeviation;
    }

    public Double getDeviationDistanceMeters() {
        return deviationDistanceMeters;
    }

    public void setDeviationDistanceMeters(Double deviationDistanceMeters) {
        this.deviationDistanceMeters = deviationDistanceMeters;
    }   

    public Boolean getDestinationReached() {
        return destinationReached;
    }

    public void setDestinationReached(
        Boolean destinationReached
    ) {
        this.destinationReached =
            destinationReached;
    }

    public String getDestinationStop() {
        return destinationStop;
    }

    public void setDestinationStop(
        String destinationStop
    ) { 
        this.destinationStop =
            destinationStop;
    }

}