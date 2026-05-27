package com.myproject.service;

import com.myproject.model.dto.request.BookingSagaRequestDto;
import com.myproject.model.dto.request.BookingSagaResponseDto;
import com.myproject.model.dto.response.BookingResponseDto;

public interface BookingSagaService {
    BookingSagaResponseDto startSaga(BookingSagaRequestDto requestDto);
}
