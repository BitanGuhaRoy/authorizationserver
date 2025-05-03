package com.example.userservice.exceptions;

public class InvalidSignupRequestException extends Exception {
    public InvalidSignupRequestException(String message) {
        super(message);
    }
}
