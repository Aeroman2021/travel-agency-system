package com.myproject.service;

import com.myproject.model.dto.response.FlightCabinResponse;
import com.myproject.model.entity.FlightCabin;
import com.myproject.model.enums.CabinClass;

import java.math.BigDecimal;

public interface FlightCabinService {
     FlightCabinResponse getById(Long id);
     FlightCabin findByFlightIdAndCabinClass(Long flightId, CabinClass cabinClass);
     void reservedSeats(Long flightId, int passengerCount, CabinClass cabinClass);
     BigDecimal calculateTotalPrice(Long flightId, CabinClass cabinClass, int passengerCount);

}
