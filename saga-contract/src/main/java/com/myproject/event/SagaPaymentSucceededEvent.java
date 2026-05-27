package com.myproject.event;

public record SagaPaymentSucceededEvent(
        Long bookingId,
        Long paymentId,
        String gatewayRef
) implements SagaEvent{
}
