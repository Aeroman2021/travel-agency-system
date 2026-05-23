package com.myproject.model.maper;

import com.myproject.model.dto.response.FlightResvPasgsResponseDto;
import com.myproject.model.entity.FlightReservationPassenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightReservationPassengerMapper {
    FlightResvPasgsResponseDto toDto(FlightReservationPassenger flightReservationPassenger);
}
