package com.myproject.event.compensationevents;

import com.myproject.event.SagaCompensationEvent;

public record RevokeTicketEvent(Long bookingId) implements SagaCompensationEvent {
}
