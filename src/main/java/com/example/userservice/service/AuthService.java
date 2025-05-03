package com.example.userservice.service;

import com.example.userservice.exceptions.InvalidSignupRequestException;
import com.example.userservice.model.User;

import java.util.Optional;

public interface AuthService {

    User signUp(String email, String password) throws Exception;

    String login (String email, String password) throws Exception;

    void logout(String token);

    Optional<User> ValidateToken(String token);
}
