package com.myproject.repository;

import com.myproject.model.entity.FlightCabin;
import com.myproject.model.enums.CabinClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightCabinRepository extends JpaRepository<FlightCabin,Long> {

    FlightCabin findByFlightIdAndCabinClass(Long flightId, CabinClass cabinClass);

}
