package com.myproject.event;


public record BookingInitiatedEvent(Long bookingId, Long flightId, int passengerCount
) {
}