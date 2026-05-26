package com.myproject.application.flightreservationfacade;

import java.math.BigDecimal;

public interface FlightReservationFacade {
    void cancelFlightReservation(Long flightReservationId);
    void failFlightReservation(Long flightReservationId);
}
