package com.myproject.event;


public record SagaBookingInitiatedEvent(Long bookingId, Long flightId, int passengerCount
) implements SagaEvent{
}