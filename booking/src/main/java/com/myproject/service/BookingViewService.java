package com.myproject.service;

import com.myproject.model.entity.BookingView;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookingViewService {
    Page<BookingView> findBookingViewByCurrentUserId(String currentUserId, String pageNumber, String pageSize);
    BookingView findBookingViewById(Long bookingId);

}
