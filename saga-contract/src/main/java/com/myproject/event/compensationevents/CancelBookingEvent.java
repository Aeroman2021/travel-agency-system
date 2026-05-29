package com.myproject.event.compensationevents;

import com.myproject.event.SagaCompensationEvent;

public record CancelBookingEvent(Long bookingId) implements SagaCompensationEvent {
}
