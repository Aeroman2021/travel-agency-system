package com.myproject.service;

import com.myproject.model.dto.response.PaymentResponseDto;
import com.myproject.event.PassengerRegisteredEvent;

public interface PaymentService  {
    PaymentResponseDto processPayment(PassengerRegisteredEvent event);
    void refundPayment(Long paymentId);

}
