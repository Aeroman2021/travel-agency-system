package com.myproject.service;

import com.myproject.model.dto.request.BookingRequestDto;
import com.myproject.model.dto.response.BookingResponseDto;

public interface BookingService {
    BookingResponseDto save(BookingRequestDto requestDto);
}
