package com.myproject.application.flightcabinfacade;

import com.myproject.model.dto.response.FlightCabinResponse;
import com.myproject.model.entity.FlightCabin;
import com.myproject.model.enums.CabinClass;

import java.math.BigDecimal;

public interface FlightCabinFacade {
    BigDecimal calculateTotalPrice(Long flightId, CabinClass cabinClass,int passengerCount);
    FlightCabin findByFlightIdAndCabinClass(Long flightId, CabinClass cabinClass);

}
