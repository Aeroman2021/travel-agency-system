package com.myproject.event;


import java.math.BigDecimal;

public record SagaFlightReservedEvent(
        Long flightId,
        Long bookingId,
        BigDecimal totalPrice,
        int passengerCount

) implements SagaEvent{
}
