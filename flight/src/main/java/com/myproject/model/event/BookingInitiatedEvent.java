package com.myproject.model.event;


public record BookingInitiatedEvent(Long bookingId, Long flightId, int passengerCount
) {
}