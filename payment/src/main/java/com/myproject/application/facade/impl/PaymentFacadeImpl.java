package com.myproject.application.facade.impl;

import com.myproject.application.facade.PaymentFacade;
import com.myproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFacadeImpl implements PaymentFacade {

    private final PaymentService paymentService;

    @Override
    public void refundPayment(Long paymentId) {
        paymentService.refundPayment(paymentId);
    }
}
