package com.myproject.application;

public interface FlightFacade {
    FlightPricingDto getFlightPriceByFlightId(Long flightId);
}
