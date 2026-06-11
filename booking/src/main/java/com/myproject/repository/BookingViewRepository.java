package com.myproject.repository;

import com.myproject.model.entity.BookingView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingViewRepository extends JpaRepository<BookingView,Long> {
    BookingView findBookingViewByCurrentUserId(String currentUserId);
}
