package com.myproject.event;


import com.myproject.model.enums.CabinClass;

public record BookingInitiatedEvent(
        Long bookingId,
        Long flightId,
        Long flightCabinId,
        int passengerCount
){
}