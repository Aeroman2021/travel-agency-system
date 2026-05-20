package com.myproject.exception;

public class SeatAlreadyReservedException extends RuntimeException{
    public SeatAlreadyReservedException(String message) {
        super(message);
    }
}
