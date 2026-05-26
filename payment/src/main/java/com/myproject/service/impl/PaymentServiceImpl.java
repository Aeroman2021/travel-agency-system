package com.myproject.service.impl;

import com.myproject.application.BookingFacade;
import com.myproject.model.dto.response.PaymentResponseDto;
import com.myproject.model.entity.Payment;
import com.myproject.model.enums.PaymentStatus;
import com.myproject.event.PassengerRegisteredEvent;
import com.myproject.event.PaymentSucceededEvent;
import com.myproject.model.mapper.PaymentMapper;
import com.myproject.repository.PaymentRepository;
import com.myproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final BookingFacade bookingFacade;
    private final ApplicationEventPublisher eventPublisher;


    @Override
    public PaymentResponseDto processPayment(PassengerRegisteredEvent event) {
        var payment = new Payment();
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setBookingId(event.bookingId());
        payment.setPrice(bookingFacade.getTotalPriceById(event.bookingId()));
        payment.setGatewayRef(createGatewayRef());

        var savedPayment = paymentRepository.save(payment);
        eventPublisher.publishEvent(
                new PaymentSucceededEvent(
                        event.bookingId(),
                        savedPayment.getId(),
                        savedPayment.getGatewayRef()));

        return paymentMapper.toDto(savedPayment);
    }

    private String createGatewayRef() {
        return UUID.randomUUID().toString().replace("-","").substring(0, 10).toUpperCase();
    }

}
