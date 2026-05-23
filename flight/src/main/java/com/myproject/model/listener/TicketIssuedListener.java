package com.myproject.model.listener;

import com.myproject.event.TicketSuccessfullyIssuedEvent;
import com.myproject.service.FlightResvPsgrsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TicketIssuedListener {
    private final FlightResvPsgrsService flightResvPsgrsService;

    @EventListener
    public void handleTicketSuccessfullyIssued(TicketSuccessfullyIssuedEvent event) {

        log.info("Received ticket issued event with booking id: {}",
                event.flightReservationDtoList().getFirst().getBookingId());
        flightResvPsgrsService.save(event);
    }
}
