package com.leticiaamancio.travelorder.flight;

import java.util.Objects;

public class Flight {
    private Long travelOrderId;
    private String fromAirport;
    private String toAirport;

    public Flight(String toAirport, String fromAirport, Long travelOrderId) {
        this.toAirport = toAirport;
        this.fromAirport = fromAirport;
        this.travelOrderId = travelOrderId;
    }

    public Flight() {
    }

    public Long getTravelOrderId() {
        return travelOrderId;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setTravelOrderId(Long travelOrderId) {
        this.travelOrderId = travelOrderId;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flight that = (Flight) o;
        return Objects.equals(travelOrderId, that.travelOrderId) && Objects.equals(fromAirport, that.fromAirport) && Objects.equals(toAirport, that.toAirport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelOrderId, fromAirport, toAirport);
    }
}
