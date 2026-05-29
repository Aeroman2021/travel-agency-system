package com.myproject.event.progressevents;


import com.myproject.event.SagaProgressEvent;

public record SagaBookingInitiatedEvent(Long bookingId, Long flightId, int passengerCount
) implements SagaProgressEvent {
}