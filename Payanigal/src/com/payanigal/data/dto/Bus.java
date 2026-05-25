package com.payanigal.data.dto;
public class Bus {

    private Long id;
    private String busNumber;
    private String operatorName;
    private BusType busType;
    private int totalSeats;
    private BusStatus status;
    public enum BusType   { AC_SLEEPER, AC_SEATER, NON_AC_SLEEPER, NON_AC_SEATER }
    public enum BusStatus { ACTIVE, INACTIVE }
    public Bus() {
    }
    public Long getId()                      { return id; }
    public void setId(Long id)               { this.id = id; }
    public String getBusNumber()             { return busNumber; }
    public void setBusNumber(String n)       { this.busNumber = n; }
    public String getOperatorName()          { return operatorName; }
    public void setOperatorName(String n)    { this.operatorName = n; }
    public BusType getBusType()              { return busType; }
    public void setBusType(BusType t)        { this.busType = t; }
    public int getTotalSeats()               { return totalSeats; }
    public void setTotalSeats(int s)         { this.totalSeats = s; }
    public BusStatus getStatus()             { return status; }
    public void setStatus(BusStatus s)       { this.status = s; }
    public static String labelFor(BusType t) {
        if (t == null) return "-";
        switch (t) {
            case AC_SLEEPER:     return "AC Sleeper";
            case AC_SEATER:      return "AC Seater";
            case NON_AC_SLEEPER: return "Non-AC Sleeper";
            case NON_AC_SEATER:  return "Non-AC Seater";
            default:             return t.name();
        }
    }
}
