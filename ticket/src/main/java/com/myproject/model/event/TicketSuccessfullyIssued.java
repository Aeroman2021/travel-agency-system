package com.myproject.model.event;

import com.myproject.model.dto.FlightReservationDto;

public record TicketSuccessfullyIssued(
       FlightReservationDto flightReservationDto
) {
}
