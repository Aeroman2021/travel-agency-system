package com.myproject.listener;

import com.myproject.event.BookingInitiatedEvent;
import com.myproject.service.FlightReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FlightBookingListener {

    private final FlightReservationService flightReservationService;

    @EventListener
    public void handleFlightBookingInitiated(BookingInitiatedEvent event) {

        log.info("Received booking movement event: {}",
                event.bookingId());

        flightReservationService.reserveFlight(event);
    }
}
