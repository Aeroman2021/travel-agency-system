package com.myproject.event.progressevents;

import com.myproject.event.SagaProgressEvent;

public record SagaPassengerRegisteredEvent(Long bookingId
) implements SagaProgressEvent {

}
