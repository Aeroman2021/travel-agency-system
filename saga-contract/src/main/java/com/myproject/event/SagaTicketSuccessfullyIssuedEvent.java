package com.myproject.event;
import com.myproject.event.dto.FlightReservationDto;

import java.util.List;

public record SagaTicketSuccessfullyIssuedEvent(
       List<FlightReservationDto> flightReservationDtoList,
       Long bookingId

) implements SagaEvent{
}
