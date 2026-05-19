package com.myproject.model.dto.response;

import com.myproject.model.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class BookingResponseDto {

    Long id;

    Long userId;

    Long FlightId;

    BigDecimal totalPrice;

    String currencyCode;

    BookingStatus bookingStatus;

    LocalDateTime createdAt;

}
