package com.myproject.model.dto.request;

import com.myproject.model.enums.SagaStatus;
import com.myproject.model.enums.SagaStep;

import java.time.LocalDateTime;

public record BookingSagaRequestDto(
        Long bookingId


) {
}
