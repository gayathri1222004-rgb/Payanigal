package com.payanigal.data.dto;
public class Booking {
    private Long id;
    private String pnr;
    private Long userId;
    private String origin;
    private String destination;
    private String travelDate;
    private int passengerCount;
    private double totalFare;
    private BookingStatus status;
    private Long bookedAt;
    private Long cancelledAt;
    private String cancellationReason;
    public enum BookingStatus { CONFIRMED, CANCELLED }
    public Booking() {}
    public Long getId() { return id; }
    public void setId(Long id){ this.id = id; }
    public String getPnr(){ return pnr; }
    public void setPnr(String pnr)  { this.pnr = pnr; }
    public Long getUserId()  { return userId; }
    public void setUserId(Long userId)  { this.userId = userId; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination)   { this.destination = destination; }
    public String getTravelDate() { return travelDate; }
    public void setTravelDate(String travelDate)     { this.travelDate = travelDate; }
    public int getPassengerCount()  { return passengerCount; }
    public void setPassengerCount(int n) { this.passengerCount = n; }
    public double getTotalFare(){ return totalFare; }
    public void setTotalFare(double f) { this.totalFare = f; }
    public BookingStatus getStatus(){ return status; }
    public void setStatus(BookingStatus s)  { this.status = s; }
    public Long getBookedAt()  { return bookedAt; }
    public void setBookedAt(Long t) { this.bookedAt = t; }
    public Long getCancelledAt()  { return cancelledAt; }
    public void setCancelledAt(Long t) { this.cancelledAt = t; }
    public String getCancellationReason() { return cancellationReason; }
    public void setCancellationReason(String r) { this.cancellationReason = r; }
}
