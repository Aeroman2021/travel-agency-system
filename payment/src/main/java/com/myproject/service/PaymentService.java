package com.myproject.service;

import com.myproject.model.event.FlightReservedEvent;
import com.myproject.model.dto.response.PaymentResponseDto;
import com.myproject.model.event.PassengerRegisteredEvent;

public interface PaymentService  {
    PaymentResponseDto processPayment(PassengerRegisteredEvent event);
}
