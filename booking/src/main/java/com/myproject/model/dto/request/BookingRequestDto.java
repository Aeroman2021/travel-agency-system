package com.myproject.model.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class BookingRequestDto {
    Long userId;
    Long FlightId;
    int numberOfPassengers;
    String currencyCode;

}
