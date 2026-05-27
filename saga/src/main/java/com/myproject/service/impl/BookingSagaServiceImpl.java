package com.myproject.service.impl;

import com.myproject.model.dto.request.BookingSagaRequestDto;
import com.myproject.model.dto.request.BookingSagaResponseDto;
import com.myproject.model.entity.BookingSaga;
import com.myproject.model.enums.SagaStatus;
import com.myproject.model.enums.SagaStep;
import com.myproject.model.mapper.BookingSagaMapper;
import com.myproject.repository.BookingSagaRepository;
import com.myproject.service.BookingSagaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingSagaServiceImpl implements BookingSagaService {

    private final BookingSagaRepository bookingSagaRepository;
    private final BookingSagaMapper bookingSagaMapper;

    @Override
    @Transactional
    public BookingSagaResponseDto startSaga(BookingSagaRequestDto requestDto) {
        var bookingSaga = new BookingSaga();
        bookingSaga.setBookingId(requestDto.bookingId());
        bookingSaga.setStatus(SagaStatus.STARTED);
        bookingSaga.setCurrentStep(SagaStep.BOOKING_CREATED);

        BookingSaga savedBookingSaga = bookingSagaRepository.save(bookingSaga);
        return bookingSagaMapper.toDto(savedBookingSaga);
    }
}
