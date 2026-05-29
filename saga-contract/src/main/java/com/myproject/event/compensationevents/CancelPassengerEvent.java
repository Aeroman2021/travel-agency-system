package com.myproject.event.compensationevents;

import com.myproject.event.SagaCompensationEvent;

public record CancelPassengerEvent(Long bookingId) implements SagaCompensationEvent {
}
