package com.myproject.event.failedevents;

import com.myproject.event.SagaFailureEvent;

public record SagaTicketIssuedFailedEvent(Long bookingId,
                                          String reason) implements SagaFailureEvent {

}
