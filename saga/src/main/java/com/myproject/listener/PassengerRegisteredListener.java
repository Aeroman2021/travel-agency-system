package com.myproject.listener;

import com.myproject.event.PassengerRegisteredEvent;
import com.myproject.service.BookingSagaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PassengerRegisteredListener {
    private final BookingSagaService bookingSagaService;

    @EventListener
    public void onPassengerRegistered(PassengerRegisteredEvent event) {
        log.info("Received PassengerRegistered  event with bookingId : {}", event.bookingId());
        bookingSagaService.handlePassengerRegistered(event);
    }
}
