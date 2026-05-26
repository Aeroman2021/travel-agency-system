package com.myproject.application.facade.impl;

import com.myproject.application.facade.TicketFacade;
import com.myproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketFacadeImpl implements TicketFacade {

    private final TicketService ticketService;

    @Override
    public void cancelTicketByBookingId(Long bookingId) {
        ticketService.cancelTicketByBookingId(bookingId);
    }
}
