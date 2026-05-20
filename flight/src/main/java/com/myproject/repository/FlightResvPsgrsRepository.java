package com.myproject.repository;

import com.myproject.model.entity.FlightReservationPassenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightResvPsgrsRepository extends JpaRepository<FlightReservationPassenger,Long> {
}
