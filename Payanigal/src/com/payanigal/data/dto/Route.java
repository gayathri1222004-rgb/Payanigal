package com.payanigal.data.dto;
public class Route {
    private Long   id;
    private String origin;
    private String destination;
    private int distanceKm;
    public Route() {
    }
    public Long getId()  { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOrigin()  { return origin; }
    public void setOrigin(String o) { this.origin = o; }
    public String getDestination() { return destination; }
    public void setDestination(String d)   { this.destination = d; }
    public int getDistanceKm()  { return distanceKm; }
    public void setDistanceKm(int d) { this.distanceKm = d; }
    public String getDisplay() {
        return origin + " -> " + destination;
    }
}
