package com.myproject.application;

import java.math.BigDecimal;

public interface FlightFacade {
    FlightPricingDto getFlightPrice(Long flightId);
    BigDecimal calculateTotalPrice(Long flightId, int passengerCount);
    void hasAvailableSeats(Long flightId, int passengerCount);
}
