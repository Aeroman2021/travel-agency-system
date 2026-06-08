package com.myproject.model.maper;

import com.myproject.model.dto.response.FlightViewDto;
import com.myproject.model.view.FlightView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface FlightViewMapper {

    @Mapping(target = "availableCabins",ignore = true)
    @Mapping(target = "startingPrice",source = "startingPrice")
    FlightViewDto toDto(FlightView flightView);
}
