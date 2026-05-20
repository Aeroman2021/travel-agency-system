package com.myproject.service;

import com.myproject.model.dto.PassengerRequestDto;
import com.myproject.model.dto.PassengerResponseDto;

public interface PassengerService {
    PassengerResponseDto save(PassengerRequestDto requestDto);
}
