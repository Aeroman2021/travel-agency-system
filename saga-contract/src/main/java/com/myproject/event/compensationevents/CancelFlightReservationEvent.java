package com.myproject.event.compensationevents;

import com.myproject.event.SagaCompensationEvent;

public record CancelFlightReservationEvent(Long bookingId) implements SagaCompensationEvent {
}
