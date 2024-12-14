package com.leticiaamancio.travelorder.hotel;

import java.util.Objects;

public class Hotel {
    private Long travelOrderId;
    private Integer nights;

    public Hotel(Long travelOrderId, Integer nights) {
        this.travelOrderId = travelOrderId;
        this.nights = nights;
    }

    public Hotel() {
    }

    public Long getTravelOrderId() {
        return travelOrderId;
    }

    public Integer getNights() {
        return nights;
    }

    public void setTravelOrderId(Long travelOrderId) {
        this.travelOrderId = travelOrderId;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(travelOrderId, hotel.travelOrderId) && Objects.equals(nights, hotel.nights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelOrderId, nights);
    }
}
