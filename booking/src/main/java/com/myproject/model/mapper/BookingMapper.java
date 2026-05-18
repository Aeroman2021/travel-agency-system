package com.myproject.model.mapper;


import com.myproject.model.dto.request.BookingRequestDto;
import com.myproject.model.dto.response.BookingResponseDto;
import com.myproject.model.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingResponseDto toDto(Booking booking);

    Booking toEntity(BookingRequestDto requestDto);
}
