package com.myproject.event.failedevents;

import com.myproject.event.SagaEvent;

public record SagaPassengerRegisteredFailedEvent(Long bookingId,
                                                 String reason)implements SagaEvent {
}
