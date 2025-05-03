package com.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpresponseDto {

    private String message;
    private RequestStatus status;
}
