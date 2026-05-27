package com.myproject.model.mapper;


import com.myproject.model.dto.request.BookingSagaRequestDto;
import com.myproject.model.dto.request.BookingSagaResponseDto;
import com.myproject.model.entity.BookingSaga;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingSagaMapper {
    BookingSaga toEntity(BookingSagaRequestDto requestDto);
    BookingSagaResponseDto toDto(BookingSaga bookingSaga);
}
