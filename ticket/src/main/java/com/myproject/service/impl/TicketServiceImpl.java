package com.myproject.service.impl;

import com.myproject.model.dto.response.TicketResponseDto;
import com.myproject.model.entity.Ticket;
import com.myproject.model.event.PaymentSucceededEvent;
import com.myproject.model.mapper.TicketMapper;
import com.myproject.repository.TicketRepository;
import com.myproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper tickerMapper;

    @Override
    public TicketResponseDto issueTicket(PaymentSucceededEvent paymentSucceededEvent) {
        Ticket ticket = new Ticket();
        ticket.setBookingId(paymentSucceededEvent.bookingId());
        ticket.setTicketNumber(ticketNumberGenerator());

        var savedTicket = ticketRepository.save(ticket);
        return tickerMapper.toDto(savedTicket);
    }

    private String ticketNumberGenerator() {
        return UUID.randomUUID().toString().replace("-", "").substring(8).toUpperCase();
    }
}
