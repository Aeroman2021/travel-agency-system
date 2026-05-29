package com.myproject.repository;

import com.myproject.model.entity.FlightReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightReservationRepository extends JpaRepository<FlightReservation,Long> {
    Optional<FlightReservation> findByBookingId(Long bookingId);
}
