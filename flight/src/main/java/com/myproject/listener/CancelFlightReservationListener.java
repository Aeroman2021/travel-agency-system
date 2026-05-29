package com.myproject.listener;

import com.myproject.event.compensationevents.CancelFlightReservationEvent;
import com.myproject.service.FlightReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelFlightReservationListener {

    private final FlightReservationService flightReservationService;

    @EventListener
    public void handle(CancelFlightReservationEvent event){
        flightReservationService.cancelFlightReservation(event);
    }
}
