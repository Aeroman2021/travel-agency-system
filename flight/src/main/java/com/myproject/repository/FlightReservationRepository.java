package com.myproject.repository;

import com.myproject.model.entity.FlightReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightReservationRepository extends JpaRepository<FlightReservation,Long> {
}
