package com.myproject.service;

import com.myproject.event.PaymentSucceededEvent;
import com.myproject.event.compensationevents.RevokeTicketEvent;
import com.myproject.model.dto.response.TicketResponseDto;

import java.util.List;

public interface TicketService {
    List<TicketResponseDto> issueTicket(PaymentSucceededEvent paymentSucceededEvent);

    void cancelTicketByBookingId(RevokeTicketEvent event);
}
