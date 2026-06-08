package com.myproject.application.flightcabinfacade;

import com.myproject.model.dto.response.FlightCabinResponse;
import com.myproject.model.entity.FlightCabin;
import com.myproject.model.enums.CabinClass;

import java.math.BigDecimal;

public interface FlightCabinFacade {
    FlightCabinResponse getById(Long id);
    void releaseSeats(Long flightId, CabinClass cabinClass, int seatCount);
    BigDecimal calculateTotalPrice(Long flightId, CabinClass cabinClass,int passengerCount);
    void hasAvailableSeats(Long flightId,CabinClass cabinClass, int passengerCount);
    FlightCabin findByFlightIdAndCabinClass(Long flightId, CabinClass cabinClass);

}
