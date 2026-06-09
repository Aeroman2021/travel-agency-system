package com.myproject.model.dto.request;

import com.myproject.model.enums.CabinClass;

public record BookingRequestDto(
        Long flightId,
        CabinClass cabinClass,
        int numberOfPassengers
) {
}
