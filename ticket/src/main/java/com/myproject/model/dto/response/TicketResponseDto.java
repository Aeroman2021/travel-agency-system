package com.myproject.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TicketResponseDto {

    private Long id;
    private Long bookingId;
    private Long passengerId;
    private String ticketNumber;
    private LocalDateTime issuedAt;
}
