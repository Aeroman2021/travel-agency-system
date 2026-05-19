package com.myproject.exception;

public class NoAvailableSeatsException extends RuntimeException{
    public NoAvailableSeatsException(String message) {
        super(message);
    }
}
