package com.myproject.model.listener;

import com.myproject.model.event.FlightReservedEvent;
import com.myproject.model.event.PassengerRegisteredEvent;
import com.myproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PassengersRegisteredListener {
    private final PaymentService paymentService;

    @EventListener
    public void handleFlightBookingInitiated(PassengerRegisteredEvent event) {

        log.info("Received flight reservation  event: {}",
                event.bookingId());
        paymentService.processPayment(event);
    }
}
