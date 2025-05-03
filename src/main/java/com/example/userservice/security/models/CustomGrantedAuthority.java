package com.example.userservice.security.models;

import com.example.userservice.model.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
    private  String authority;
    private static final long serialVersionUID = 5695296084559720529L;

    public CustomGrantedAuthority() {
    }
    CustomGrantedAuthority(Role role) {
        this.authority = role.getName();
    }
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
