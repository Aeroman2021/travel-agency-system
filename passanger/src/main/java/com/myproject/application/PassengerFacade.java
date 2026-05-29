package com.myproject.application;

import com.myproject.model.dto.response.PassengerResponseDto;

import java.util.List;

public interface PassengerFacade {
    List<PassengerResponseDto> getPassengerByBookId(Long bookId);

}
