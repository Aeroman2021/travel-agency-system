package com.myproject.model.maper;

import com.myproject.model.dto.response.FlightResponseDto;
import com.myproject.model.entity.Flight;

public class FlightMapper {

    public static FlightResponseDto toDto(Flight flight){
        return FlightResponseDto.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airLine(flight.getAirLine().getId())
                .originAirport(flight.getOriginAirport().getId())
                .destinationAirport(flight.getDestinationAirport().getId())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .status(flight.getStatus())
                .build();
    }

}
