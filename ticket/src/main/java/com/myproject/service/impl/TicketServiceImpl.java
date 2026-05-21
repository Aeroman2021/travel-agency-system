package com.myproject.service.impl;

import com.myproject.application.PassengerFacade;
import com.myproject.model.dto.FlightReservationDto;
import com.myproject.model.dto.response.TicketResponseDto;
import com.myproject.model.entity.Ticket;
import com.myproject.model.event.PaymentSucceededEvent;
import com.myproject.model.event.TicketSuccessfullyIssued;
import com.myproject.model.mapper.TicketMapper;
import com.myproject.repository.TicketRepository;
import com.myproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper tickerMapper;
    private final PassengerFacade passengerFacade;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<TicketResponseDto> issueTicket(PaymentSucceededEvent paymentSucceededEvent) {

        List<TicketResponseDto> ticketResponseDtoList = passengerFacade
                .getPassengerByBookId(paymentSucceededEvent.bookingId())
                .stream()
                .map(e -> {
                    Ticket ticket = new Ticket();
                    ticket.setBookingId(e.getBookingId());
                    ticket.setPassengerId(e.getId());
                    ticket.setTicketNumber(generateTicketNumber());
                    var savedTicket = ticketRepository.save(ticket);
                    return tickerMapper.toDto(savedTicket);
                }).toList();

        eventPublisher.publishEvent(generateTicketSuccessfullyIssuedEvent(ticketResponseDtoList, paymentSucceededEvent));

        return ticketResponseDtoList;
    }


    private TicketSuccessfullyIssued generateTicketSuccessfullyIssuedEvent(
            List<TicketResponseDto> ticketResponseDtoList,
            PaymentSucceededEvent paymentSucceededEvent) {

        List<String> ticketNumebrList = ticketResponseDtoList.stream()
                .map(TicketResponseDto::getTicketNumber)
                .toList();

        List<Long> passengerIdList = ticketResponseDtoList.stream()
                .map(TicketResponseDto::getPassengerId)
                .toList();

        var bookingId = paymentSucceededEvent.bookingId();

        return new TicketSuccessfullyIssued(new FlightReservationDto(ticketNumebrList, passengerIdList, bookingId));
    }

    private String generateTicketNumber() {
        return UUID
                .randomUUID()
                .toString()
                .replace("-", "")
                .substring(8)
                .toUpperCase();
    }
}
