package com.myproject.event.progressevents;

import com.myproject.event.SagaProgressEvent;

public record SagaPaymentSucceededEvent(
        Long bookingId,
        Long paymentId,
        String gatewayRef
) implements SagaProgressEvent {
}
