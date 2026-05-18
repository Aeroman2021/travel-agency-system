package com.myproject.application;

import java.math.BigDecimal;

public record FlightPricingDto(
        Long flightId,
        BigDecimal price
) {
}
