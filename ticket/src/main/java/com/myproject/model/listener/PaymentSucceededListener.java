package com.myproject.model.listener;

import com.myproject.event.PaymentSucceededEvent;
import com.myproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentSucceededListener {

    private final TicketService ticketService;

    @EventListener
    public void handlePaymentSucceededEvent(PaymentSucceededEvent event) {

        log.info("EVENT RECEIVED");

        try {

            ticketService.issueTicket(event);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
