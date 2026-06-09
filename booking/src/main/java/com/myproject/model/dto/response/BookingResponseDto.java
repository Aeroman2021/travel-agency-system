package com.myproject.model.dto.response;

import com.myproject.model.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
public class BookingResponseDto {

    Long id;

    private String currentUserId;;

    Long flightId ;

    private Long flightCabinId;

    int numberOfPassengers;

    BigDecimal totalPrice;

    String currencyCode;

    BookingStatus bookingStatus;

    LocalDateTime createdAt;

}
