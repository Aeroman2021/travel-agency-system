package com.myproject.event;


public record BookingInitiatedEvent(Long bookingId,Long flightId,Long flightCabin, int passengerCount
){
}