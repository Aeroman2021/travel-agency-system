package com.myproject.event.progressevents;

import com.myproject.TicketIssueResult;
import com.myproject.event.SagaProgressEvent;

import java.util.List;

public record SagaTicketSuccessfullyIssuedEvent(
       List<TicketIssueResult> ticketIssueResults,
       Long bookingId

) implements SagaProgressEvent {

}






