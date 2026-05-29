package com.myproject.service.impl;

import com.myproject.enums.SagaStatus;
import com.myproject.event.SagaEvent;
import com.myproject.event.compensationevents.*;
import com.myproject.repository.BookingSagaRepository;
import com.myproject.service.BookingSagaService;
import com.myproject.utils.SagaTransitionPolicy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static com.myproject.enums.SagaStep.TICKET_ISSUED;


@Service
@RequiredArgsConstructor
public class BookingSagaServiceImpl implements BookingSagaService {

    private final BookingSagaRepository bookingSagaRepository;
    private final SagaTransitionPolicy sagaTransitionPolicy;
    private final ApplicationEventPublisher eventPublisher;


    @Override
    @Transactional
    public void handleSagaEvent(SagaEvent sagaEvent) {
        var bookingSaga = bookingSagaRepository.findByBookingId(sagaEvent.bookingId());
        var currentStep = bookingSaga.getCurrentStep();
        bookingSaga.setCurrentStep(sagaTransitionPolicy.nextStep(currentStep));
        bookingSaga.setStatus(SagaStatus.STARTED);

        if(currentStep.equals(TICKET_ISSUED))
            bookingSaga.setStatus(SagaStatus.COMPLETED);
    }

    @Override
    @Transactional
    public void compensateSagaEvent(SagaEvent sagaEvent) {
        var bookingId = sagaEvent.bookingId();
        var saga = bookingSagaRepository.findByBookingId(sagaEvent.bookingId());
        saga.setStatus(SagaStatus.COMPENSATING);


        switch (saga.getCurrentStep()) {

            case TICKET_ISSUED -> {
                eventPublisher.publishEvent(new RevokeTicketEvent(bookingId));
                eventPublisher.publishEvent(new RefundPaymentEvent(bookingId));
                eventPublisher.publishEvent(new CancelPassengerEvent(bookingId));
                eventPublisher.publishEvent(new CancelFlightReservationEvent(bookingId));
                eventPublisher.publishEvent(new CancelBookingEvent(bookingId));
            }

            case PAYMENT_COMPLETED -> {
                eventPublisher.publishEvent(new RefundPaymentEvent(bookingId));
                eventPublisher.publishEvent(new CancelPassengerEvent(bookingId));
                eventPublisher.publishEvent(new CancelFlightReservationEvent(bookingId));
                eventPublisher.publishEvent(new CancelBookingEvent(bookingId));
            }

            case PASSENGERS_REGISTERED -> {
                eventPublisher.publishEvent(new CancelPassengerEvent(bookingId));
                eventPublisher.publishEvent(new CancelFlightReservationEvent(bookingId));
                eventPublisher.publishEvent(new CancelBookingEvent(bookingId));
            }

            case FLIGHT_RESERVED -> {
                eventPublisher.publishEvent(new CancelFlightReservationEvent(bookingId));
                eventPublisher.publishEvent(new CancelBookingEvent(bookingId));
            }

            case BOOKING_CREATED -> eventPublisher.publishEvent(new CancelBookingEvent(bookingId));

        }

        saga.setStatus(SagaStatus.COMPENSATING);
    }

}
