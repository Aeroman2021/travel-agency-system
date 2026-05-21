package com.myproject.model.mapper;

import com.myproject.model.dto.response.TicketResponseDto;
import com.myproject.model.entity.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketResponseDto toDto(Ticket ticket);
}
