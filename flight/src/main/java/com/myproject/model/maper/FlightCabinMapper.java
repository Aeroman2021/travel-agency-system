package com.myproject.model.maper;

import com.myproject.model.dto.response.FlightCabinResponse;
import com.myproject.model.entity.FlightCabin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlightCabinMapper {
    @Mapping(source = "flight.id",target = "flightId")
    FlightCabinResponse toDto(FlightCabin flightCabin);
}
