package com.myproject.listener;

import com.myproject.event.SagaEvent;
import com.myproject.event.SagaFailureEvent;
import com.myproject.service.BookingSagaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SagaListener {

    private final BookingSagaService bookingSagaService;

    @EventListener
    public void onBookingInitiated(SagaEvent event) {
        log.info("Received Booking  event: {}", event.bookingId());

        if(event instanceof SagaFailureEvent){
            bookingSagaService.compensateSagaEvent(event);
        }else {
            bookingSagaService.handleSagaEvent(event);
        }

    }
}
