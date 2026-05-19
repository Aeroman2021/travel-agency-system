package com.myproject.exception;

public class InvalidInputSeatNumber extends RuntimeException{
    public InvalidInputSeatNumber(String message) {
        super(message);
    }
}
