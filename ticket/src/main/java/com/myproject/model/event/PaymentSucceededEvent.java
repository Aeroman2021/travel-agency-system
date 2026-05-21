package com.myproject.model.event;

public record PaymentSucceededEvent(
        Long bookingId,
        Long paymentId,
        String gatewayRef
) {
}
