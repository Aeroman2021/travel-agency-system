package com.myproject.event;

import com.myproject.TicketIssueResult;

import java.util.List;

public record SagaTicketSuccessfullyIssuedEvent(
       List<TicketIssueResult> ticketIssueResults,
       Long bookingId

) implements SagaEvent{



}






