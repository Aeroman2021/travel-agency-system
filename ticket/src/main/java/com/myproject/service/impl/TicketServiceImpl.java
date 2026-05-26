package com.myproject.service.impl;

import com.myproject.application.PassengerFacade;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.model.dto.FlightReservationDto;
import com.myproject.model.dto.response.TicketResponseDto;
import com.myproject.model.entity.Ticket;
import com.myproject.model.enums.TicketStatus;
import com.myproject.model.event.PaymentSucceededEvent;
import com.myproject.model.event.TicketSuccessfullyIssuedEvent;
import com.myproject.event.PaymentSucceededEvent;
import com.myproject.event.TicketSuccessfullyIssuedEvent;
import com.myproject.model.dto.FlightReservationDto;
import com.myproject.model.dto.response.TicketResponseDto;
import com.myproject.model.entity.Ticket;
import com.myproject.model.mapper.TicketMapper;
import com.myproject.repository.TicketRepository;
import com.myproject.service.TicketService;
import jakarta.transaction.Transactional;
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
                    Ticket savedTicket = ticketRepository.save(ticket);
                    return tickerMapper.toDto(savedTicket);
                }).toList();
        System.out.println("init publishing event");

        eventPublisher.publishEvent(generateTicketIssuedEvent(ticketResponseDtoList));
        return ticketResponseDtoList;
    }

    private TicketSuccessfullyIssuedEvent generateTicketIssuedEvent(
            List<TicketResponseDto> ticketResponseDtoList) {

        List<FlightReservationDto> list = ticketResponseDtoList
                .stream()
                .map(dto -> {
                    return new FlightReservationDto(
                            dto.getBookingId(),
                            dto.getPassengerId(),
                            dto.getTicketNumber());
                }).toList();

        return new TicketSuccessfullyIssuedEvent(list);
    }




    private String generateTicketNumber() {
        return UUID
                .randomUUID()
                .toString()
                .replace("-", "")
                .substring(6)
                .toUpperCase();
    }

    @Override
    @Transactional
    public void cancelTicketByBookingId(Long bookingId) {
        var ticket =  getTicketByBookingId(bookingId);
        ticket.setTicketStatus(TicketStatus.CANCELLED);
    }

    private Ticket getTicketByBookingId(Long bookingId) {
        return ticketRepository.findTicketByBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with bookingId %d not found"
                        .formatted("Ticket", bookingId)));
    }
}
