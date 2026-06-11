package com.myproject.model.entity;

import com.myproject.model.enums.BookingStatus;
import com.myproject.model.enums.CabinClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "vw_bookings")
@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class BookingView {
    @Id
    private Long id;

    @Column(name = "current_user_id")
    private String currentUserId;

    private Long flightId;

    private Long flightCabinId;

    @Column(name = "num_of_passengers")
    private int numberOfPassengers;

    private BigDecimal totalPrice;

    @Column(name = "currency_code")
    private String currencyCode;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(name = "flight_number")
    private String flightNumber;

    @Enumerated(EnumType.STRING)
    private CabinClass cabinClass;

    private LocalDateTime createdAt;

}
