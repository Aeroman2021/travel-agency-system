package com.myproject.event.failedevents;

import com.myproject.event.SagaFailureEvent;

public record SagaFlightReservationFailedEvent(Long bookingId,
                                               String reason) implements SagaFailureEvent {
}
