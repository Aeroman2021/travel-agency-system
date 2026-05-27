package com.myproject.listener;

import com.myproject.event.FlightReservedEvent;
import com.myproject.model.dto.request.BookingSagaRequestDto;
import com.myproject.service.BookingSagaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FlightReservedListener {

    private final BookingSagaService bookingSagaService;

    @EventListener
    public void onFlightReserved(FlightReservedEvent event) {
        log.info("Received flight reservation  event with booking id: {}", event.bookingId());
        bookingSagaService.handleFlightReserved(event);
    }

}
