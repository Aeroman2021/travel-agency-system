package com.myproject.event.failedevents;

import com.myproject.event.SagaFailureEvent;

public record SagaPaymentFailedEvent(Long bookingId, String reason) implements SagaFailureEvent {
}
