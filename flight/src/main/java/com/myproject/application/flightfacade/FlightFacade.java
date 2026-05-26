package com.myproject.application.flightfacade;

import java.math.BigDecimal;

public interface FlightFacade {
    void releaseSeats(Long flightId,int seatCount);
    BigDecimal calculateTotalPrice(Long flightId, int passengerCount);
    void hasAvailableSeats(Long flightId, int passengerCount);

}
