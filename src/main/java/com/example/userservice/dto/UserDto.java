package com.example.userservice.dto;

import com.example.userservice.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String username;
    private String email;
    private List<Role> roles;
}
