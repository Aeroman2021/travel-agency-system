package com.myproject.model.maper;

import com.myproject.model.dto.response.FlightReservationResponseDto;
import com.myproject.model.entity.FlightReservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightReservationMapper {
    FlightReservationResponseDto toDto(FlightReservation flightReservation);
}
