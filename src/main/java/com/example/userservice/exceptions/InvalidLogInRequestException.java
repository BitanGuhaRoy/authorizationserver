package com.example.userservice.exceptions;

public class InvalidLogInRequestException extends Exception {

    public InvalidLogInRequestException(String message) {
        super(message);
    }
}
