package com.myproject.model.mapper;

import com.myproject.model.dto.response.PaymentResponseDto;
import com.myproject.model.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentResponseDto toDto(Payment payment);
}
