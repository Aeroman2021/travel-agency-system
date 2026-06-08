package com.myproject.model.dto.response;

import com.myproject.model.enums.CabinClass;

import java.math.BigDecimal;

public record FlightCabinResponse(

        Long id,
        Long flightId,
        BigDecimal price,
        String currencyCode,
        CabinClass cabinClass,
        int availableSeats
) {
}
