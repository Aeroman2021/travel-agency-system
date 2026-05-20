package com.myproject.model.mapper;

import com.myproject.model.dto.PassengerRequestDto;
import com.myproject.model.dto.PassengerResponseDto;
import com.myproject.model.entity.Passenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    PassengerResponseDto toDto(Passenger passenger);
    Passenger toEntity(PassengerRequestDto requestDto);
}
