package com.myproject.service.impl;

import com.myproject.TicketIssueResult;
import com.myproject.application.PassengerFacade;
import com.myproject.event.PaymentSucceededEvent;
import com.myproject.event.progressevents.SagaTicketSuccessfullyIssuedEvent;
import com.myproject.event.TicketSuccessfullyIssuedEvent;
import com.myproject.event.compensationevents.RevokeTicketEvent;
import com.myproject.event.failedevents.SagaTicketIssuedFailedEvent;
import com.myproject.exception.ResourceNotFoundException;
import com.myproject.exception.TicketIssuingException;
import com.myproject.model.dto.FlightReservationDto;
import com.myproject.model.dto.response.TicketResponseDto;
import com.myproject.model.entity.Ticket;
import com.myproject.model.enums.TicketStatus;
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

        var bookingId = paymentSucceededEvent.bookingId();

        try{
            List<TicketResponseDto> ticketResponseDtoList = passengerFacade
                    .getPassengerByBookId(bookingId)
                    .stream()
                    .map(e -> {
                        Ticket ticket = new Ticket();
                        ticket.setBookingId(e.getBookingId());
                        ticket.setPassengerId(e.getId());
                        ticket.setTicketNumber(generateTicketNumber());
                        Ticket savedTicket = ticketRepository.save(ticket);
                        return tickerMapper.toDto(savedTicket);
                    }).toList();

            eventPublisher.publishEvent(generateTicketIssuedEvent(ticketResponseDtoList,bookingId));
            eventPublisher.publishEvent(generateSagaTicketIssuedEvent(ticketResponseDtoList,bookingId));
            return ticketResponseDtoList;
        }catch (TicketIssuingException ex){
            eventPublisher.publishEvent(new SagaTicketIssuedFailedEvent(bookingId,ex.getMessage()));
            throw ex;
        }

    }

    private TicketSuccessfullyIssuedEvent generateTicketIssuedEvent(
            List<TicketResponseDto> ticketResponseDtoList,Long bookingId) {

        List<FlightReservationDto> list = ticketResponseDtoList
                .stream()
                .map(dto -> new FlightReservationDto(
                        dto.getPassengerId(),
                        dto.getTicketNumber())).toList();

        return new TicketSuccessfullyIssuedEvent(list,bookingId);
    }

    private SagaTicketSuccessfullyIssuedEvent generateSagaTicketIssuedEvent(
            List<TicketResponseDto> ticketResponseDtoList,Long bookingId) {
        var ticketIssueResults = ticketResponseDtoList.stream()
                .map(e -> new TicketIssueResult(e.getPassengerId(), e.getTicketNumber()))
                .toList();

        return new SagaTicketSuccessfullyIssuedEvent(ticketIssueResults,bookingId);
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
    public void cancelTicketByBookingId(RevokeTicketEvent event) {
        var ticket = getTicketByBookingId(event.bookingId());
        ticket.setTicketStatus(TicketStatus.CANCELLED);
    }

    private Ticket getTicketByBookingId(Long bookingId) {
        return ticketRepository.findTicketByBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("%s with bookingId %d not found"
                        .formatted("Ticket", bookingId)));
    }

}
