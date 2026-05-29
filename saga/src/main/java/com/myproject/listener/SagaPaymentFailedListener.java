package com.myproject.listener;

import com.myproject.event.SagaEvent;
import com.myproject.event.failedevents.SagaPaymentFailedEvent;
import com.myproject.service.BookingSagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SagaPaymentFailedListener {
    private final BookingSagaService bookingSagaService;

    @EventListener
    public void handle(SagaPaymentFailedEvent event){
        bookingSagaService.handleSagaEvent(event);
    }
}
