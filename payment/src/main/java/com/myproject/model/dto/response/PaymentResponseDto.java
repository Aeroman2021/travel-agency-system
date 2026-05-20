package com.myproject.model.dto.response;

import com.myproject.model.enums.PaymentStatus;

import java.math.BigDecimal;

public record PaymentResponseDto(

        Long id,

        Long BookingId,

        BigDecimal price,

        PaymentStatus status,

        String gatewayRef
) {
}
