package com.myproject.controller;

import com.myproject.application.AuthFacade;
import com.myproject.model.entity.BookingView;
import com.myproject.service.BookingViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking-view")
@RequiredArgsConstructor
public class BookingViewController {

    private final BookingViewService bookingViewService;
    private final AuthFacade authFacade;

    @GetMapping("my-booking")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<BookingView>> getByUserId(@RequestParam(defaultValue = "0")String pageNumber,
                                                         @RequestParam(defaultValue = "10") String pageSize){
        var currentUserId = authFacade.getCurrentUser();
        System.out.println(currentUserId);
        var result = bookingViewService.findBookingViewByCurrentUserId(currentUserId,pageNumber,pageSize);
        return ResponseEntity.ok(result);
    }
}
