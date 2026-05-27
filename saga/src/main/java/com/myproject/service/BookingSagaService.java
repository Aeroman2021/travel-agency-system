package com.myproject.service;

import com.myproject.event.FlightReservedEvent;
import com.myproject.event.PassengerRegisteredEvent;
import com.myproject.event.PaymentSucceededEvent;
import com.myproject.event.TicketSuccessfullyIssuedEvent;
import com.myproject.event.BookingInitiatedEvent;

public interface BookingSagaService {
    void handleBookingInitiated(BookingInitiatedEvent event);
    void handleFlightReserved(FlightReservedEvent event);
    void handlePassengerRegistered(PassengerRegisteredEvent event);
    void handlePaymentSucceeded(PaymentSucceededEvent event);
    void handleTicketIssued(TicketSuccessfullyIssuedEvent event);
}
