package com.myproject.listener;

import com.myproject.event.compensationevents.RefundPaymentEvent;
import com.myproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefundPaymentListener {
    private final PaymentService paymentService;

    public void handle(RefundPaymentEvent event){

    }
}
