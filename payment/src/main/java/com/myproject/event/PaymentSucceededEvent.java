package com.myproject.event;

public record  PaymentSucceededEvent(
        Long bookingId,
        Long paymentId,
        String gatewayRef
) {
}
