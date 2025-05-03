package com.example.userservice.controllers;

import com.example.userservice.dto.*;
import com.example.userservice.exceptions.InvalidSignupRequestException;
import com.example.userservice.model.User;
import com.example.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpresponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpresponseDto signUpresponseDto = new SignUpresponseDto();
        try {
            User user = authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
            signUpresponseDto.setMessage("Signed up successfully");
            signUpresponseDto.setStatus(RequestStatus.SUCCESS);
        }
        catch (Exception e) {
            signUpresponseDto.setMessage(e.getMessage());
            signUpresponseDto.setStatus(RequestStatus.FAILED);

        }
        return new ResponseEntity<>(signUpresponseDto, HttpStatus.ACCEPTED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> logIn(@RequestBody LogInRequestDto logInRequestDto) {

        LoginResponseDto logInresponseDto = new LoginResponseDto();
        try {
            String token  = authService.login(logInRequestDto.getEmail(), logInRequestDto.getPassword());
            logInresponseDto.setMessage("Logged in  successfully");
            logInresponseDto.setStatus(RequestStatus.SUCCESS);
            HttpHeaders headers = new HttpHeaders();
            headers.add("authorization", token);
            return new ResponseEntity<>(logInresponseDto,headers,  HttpStatus.ACCEPTED);

        }
        catch (Exception e) {
            logInresponseDto.setMessage(e.getMessage());
            logInresponseDto.setStatus(RequestStatus.FAILED);

        }
        return new ResponseEntity<>(logInresponseDto, HttpStatus.ACCEPTED);
    }
    @PostMapping ("/validateToken")
    public ResponseEntity<UserDto > validateToken(@RequestHeader("auth")String token) {
        Optional<User> userOptional =  authService.ValidateToken(token);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        UserDto userDto = new UserDto();
        userDto.setEmail(userOptional.get().getEmail());
        userDto.setUsername(userOptional.get().getUsername());
        userDto.setRoles(new ArrayList<>(userOptional.get().getRoles()));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("auth")String token) {
        authService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
