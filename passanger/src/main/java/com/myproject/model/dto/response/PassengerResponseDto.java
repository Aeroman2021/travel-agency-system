package com.myproject.model.dto.response;

import com.myproject.model.enums.PassengerStatus;
import com.myproject.model.enums.Sex;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PassengerResponseDto {

    private Long id;

    private Long userId;

    private Long bookingId;

    private String fullName;

    private String ncode;

    private String passportNumber;

    private Sex sex;

    private LocalDate dob;

    PassengerStatus passengerStatus;

}
