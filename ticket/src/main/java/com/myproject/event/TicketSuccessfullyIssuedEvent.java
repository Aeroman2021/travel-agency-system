package com.myproject.event;

import com.myproject.model.dto.FlightReservationDto;

import java.util.List;

public record TicketSuccessfullyIssuedEvent(
       List<FlightReservationDto> flightReservationDtoList,
       Long bookingId
) {
}
