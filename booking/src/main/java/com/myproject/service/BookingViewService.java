package com.myproject.service;

import com.myproject.model.entity.BookingView;
import org.springframework.data.domain.PageImpl;

public interface BookingViewService {
    PageImpl<BookingView> findBookingViewByCurrentUserId(String currentUserId, String pageNumber, String pageSize);

}
