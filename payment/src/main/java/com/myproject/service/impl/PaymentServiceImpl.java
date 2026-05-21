package com.myproject.service.impl;

import com.myproject.model.event.FlightReservedEvent;
import com.myproject.model.dto.response.PaymentResponseDto;
import com.myproject.model.entity.Payment;
import com.myproject.model.enums.PaymentStatus;
import com.myproject.model.mapper.PaymentMapper;
import com.myproject.repository.PaymentRepository;
import com.myproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDto processPayment(FlightReservedEvent event) {
        var payment = new Payment();
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setBookingId(event.bookingId());
        payment.setPrice(event.totalPrice());
        payment.setGatewayRef(createGatewayRef());

        var savedPayment = paymentRepository.save(payment);

        return paymentMapper.toDto(savedPayment);
    }

    private String createGatewayRef() {
        return UUID.randomUUID().toString().replace("-","").substring(0, 10).toUpperCase();
    }

}
