package com.myproject.service.impl;

import com.myproject.event.FlightReservedEvent;
import com.myproject.event.PassengerRegisteredEvent;
import com.myproject.event.PaymentSucceededEvent;
import com.myproject.event.TicketSuccessfullyIssuedEvent;
import com.myproject.model.enums.SagaStatus;
import com.myproject.event.BookingInitiatedEvent;
import com.myproject.repository.BookingSagaRepository;
import com.myproject.service.BookingSagaService;
import com.myproject.service.SagaTransitionPolicy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.myproject.model.enums.SagaStep.TICKET_ISSUED;

@Service
@RequiredArgsConstructor
public class BookingSagaServiceImpl implements BookingSagaService {

    private final BookingSagaRepository bookingSagaRepository;
    private final SagaTransitionPolicy sagaTransitionPolicy;

    @Override
    @Transactional
    public void handleBookingInitiated(BookingInitiatedEvent event) {
         advanceSaga(event.bookingId());
    }

    @Override
    @Transactional
    public void handleFlightReserved(FlightReservedEvent event) {
        advanceSaga(event.bookingId());

    }

    @Override
    @Transactional
    public void handlePassengerRegistered(PassengerRegisteredEvent event) {
        advanceSaga(event.bookingId());

    }

    @Override
    @Transactional
    public void handlePaymentSucceeded(PaymentSucceededEvent event) {
        advanceSaga(event.bookingId());

    }

    @Override
    @Transactional
    public void handleTicketIssued(TicketSuccessfullyIssuedEvent event) {
        advanceSaga(event.flightReservationDtoList().getFirst().getBookingId());
    }


    private void advanceSaga(Long bookingId){
        var bookingSaga = bookingSagaRepository.findByBookingId(bookingId);
        var currentStep = bookingSaga.getCurrentStep();
        bookingSaga.setCurrentStep(sagaTransitionPolicy.nextStep(currentStep));

        if(currentStep.equals(TICKET_ISSUED))
            bookingSaga.setStatus(SagaStatus.COMPLETED);

    }
}
