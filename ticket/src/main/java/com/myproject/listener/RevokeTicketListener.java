package com.myproject.listener;

import com.myproject.event.compensationevents.RevokeTicketEvent;
import com.myproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RevokeTicketListener {

    private final TicketService ticketService;

    @EventListener
    public void handle(RevokeTicketEvent event) {
        ticketService.cancelTicketByBookingId(event);
    }
}
