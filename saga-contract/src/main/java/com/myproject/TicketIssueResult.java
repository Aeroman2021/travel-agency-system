package com.myproject;

public record TicketIssueResult(
        Long passengerId,
        String ticketNumber
) {
}
