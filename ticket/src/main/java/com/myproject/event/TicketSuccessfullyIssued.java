package com.myproject.event;

import com.myproject.model.dto.FlightReservationDto;

public record TicketSuccessfullyIssued(
       FlightReservationDto flightReservationDto
) {
}
