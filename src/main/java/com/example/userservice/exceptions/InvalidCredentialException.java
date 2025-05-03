package com.example.userservice.exceptions;

public class InvalidCredentialException extends Exception{
    public InvalidCredentialException(String message) {
        super(message);
    }
}
