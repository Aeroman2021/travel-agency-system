package com.myproject.event;


public record BookingInitiatedEvent(
        Long bookingId,
        Long flightId,
        Long flightCabinId,
        int passengerCount
){
}