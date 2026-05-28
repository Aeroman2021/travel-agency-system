package com.myproject.service.impl;

import com.myproject.utils.SagaTransitionPolicy;
import com.myproject.enums.SagaStatus;
import com.myproject.event.*;

import com.myproject.repository.BookingSagaRepository;
import com.myproject.service.BookingSagaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.myproject.enums.SagaStep.TICKET_ISSUED;


@Service
@RequiredArgsConstructor
public class BookingSagaServiceImpl implements BookingSagaService {

    private final BookingSagaRepository bookingSagaRepository;
    private final SagaTransitionPolicy sagaTransitionPolicy;


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

}
