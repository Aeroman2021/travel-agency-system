package com.myproject.service;

import com.myproject.event.compensationevents.CancelFlightReservationEvent;
import com.myproject.model.dto.response.FlightReservationResponseDto;
import com.myproject.event.BookingInitiatedEvent;

public interface FlightReservationService {
    FlightReservationResponseDto reserveFlight(BookingInitiatedEvent event);
    void cancelFlightReservation(CancelFlightReservationEvent event);

}
