package com.myproject.service;

import com.myproject.model.dto.response.TicketResponseDto;
import com.myproject.model.event.PaymentSucceededEvent;

public interface TicketService {
    TicketResponseDto issueTicket(PaymentSucceededEvent paymentSucceededEvent);
}
