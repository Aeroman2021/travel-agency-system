package com.myproject.service;

import com.myproject.model.event.FlightReservedEvent;
import com.myproject.model.dto.response.PaymentResponseDto;

public interface PaymentService  {
    PaymentResponseDto processPayment(FlightReservedEvent event);
}
