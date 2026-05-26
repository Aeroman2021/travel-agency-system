package com.myproject.service;

import com.myproject.model.dto.response.TicketResponseDto;
import com.myproject.event.PaymentSucceededEvent;

import java.util.List;

public interface TicketService {
    List<TicketResponseDto> issueTicket(PaymentSucceededEvent paymentSucceededEvent);
    void cancelTicketByBookingId(Long bookingId);
}
