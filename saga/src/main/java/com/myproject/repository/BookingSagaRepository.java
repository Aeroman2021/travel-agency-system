package com.myproject.repository;

import com.myproject.model.entity.BookingSaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingSagaRepository extends JpaRepository<BookingSaga,Long> {
    BookingSaga findByBookingId(Long bookingId);
}
