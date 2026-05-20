package com.myproject.model.dto;

import com.myproject.model.enums.Sex;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class PassengerRequestDto {

    private Long bookingId;

    private Long userId;

    private String fullName;

    private String ncode;

    private String passportNumber;

    private Sex sex;

    private LocalDate dob;
}
