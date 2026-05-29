package com.myproject.exception;

public class TicketIssuingException extends RuntimeException {

    public TicketIssuingException() {
    }

    public TicketIssuingException(String message) {
        super(message);
    }

    public TicketIssuingException(String message, Throwable cause) {
        super(message, cause);
    }
}
