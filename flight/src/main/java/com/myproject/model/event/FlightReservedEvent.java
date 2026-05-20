package com.myproject.model.event;


import java.math.BigDecimal;

public record FlightReservedEvent(
        Long flightId,
        Long bookingId,
        BigDecimal totalPrice,
        int passengerCount

) {
}
