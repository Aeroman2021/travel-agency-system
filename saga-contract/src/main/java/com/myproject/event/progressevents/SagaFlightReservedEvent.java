package com.myproject.event.progressevents;


import com.myproject.event.SagaProgressEvent;

import java.math.BigDecimal;

public record SagaFlightReservedEvent(
        Long flightId,
        Long bookingId,
        BigDecimal totalPrice,
        int passengerCount

) implements SagaProgressEvent {
}
