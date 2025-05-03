package com.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class SignUpRequestDto {

    private String email;
    private String password;
}
