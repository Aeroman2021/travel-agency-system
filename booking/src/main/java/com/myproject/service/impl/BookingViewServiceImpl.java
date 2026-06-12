package com.myproject.service.impl;

import com.myproject.model.entity.BookingView;
import com.myproject.repository.BookingViewRepository;
import com.myproject.service.BookingViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingViewServiceImpl implements BookingViewService {

    private final BookingViewRepository  bookingViewRepository;

    @Override
    public Page<BookingView> findBookingViewByCurrentUserId(String currentUserId, String pageNumber, String pageSize) {
        var pageable = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
        return bookingViewRepository.findBookingViewByCurrentUserId(currentUserId, pageable);
    }
}
