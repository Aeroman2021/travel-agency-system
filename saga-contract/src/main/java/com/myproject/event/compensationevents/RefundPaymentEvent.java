package com.myproject.event.compensationevents;

import com.myproject.event.SagaCompensationEvent;

public record RefundPaymentEvent(Long bookingId) implements SagaCompensationEvent {
}
