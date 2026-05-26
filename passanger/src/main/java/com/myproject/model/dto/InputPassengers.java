package com.myproject.model.dto;

import com.myproject.model.dto.request.PassengerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class InputPassengers {
    private List<PassengerRequestDto> passengerRequestDtoList;
    private Long bookingId;
}
