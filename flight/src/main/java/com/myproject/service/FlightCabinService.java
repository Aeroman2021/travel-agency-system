package com.myproject.service;

import com.myproject.model.entity.FlightCabin;
import com.myproject.model.enums.CabinClass;

import java.math.BigDecimal;

public interface FlightCabinService {
     FlightCabin getById(Long id);
     FlightCabin findByFlightIdAndCabinClass(Long flightId, CabinClass cabinClass);
     void reservedSeats(Long flightCabinId,int passengerCount);
     BigDecimal calculateTotalPrice(Long flightCabinId,int passengerCount);

}
