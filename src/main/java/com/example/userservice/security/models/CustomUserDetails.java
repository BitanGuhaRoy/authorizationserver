package com.example.userservice.security.models;

import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@JsonDeserialize
public class CustomUserDetails implements UserDetails {
    private Long userId;
    private  String password;
    private  String username;
    private  boolean enabled;
    private  boolean accountNonExpired;
    private  boolean accountNonLocked;
    private  boolean credentialsNonExpired;
    private  List<CustomGrantedAuthority> authorities;
    private static final long serialVersionUID = 4850335374050863333L;
    private String mobile;
    public CustomUserDetails() {
    }

    public CustomUserDetails(User user) {
        this.password = user.getPassword();
        this.username = user.getEmail();
        this.enabled = true;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        List<CustomGrantedAuthority> customGrantedAuthorities = new ArrayList<>();
        for(Role role : user.getRoles()) {
            customGrantedAuthorities.add(new CustomGrantedAuthority(role));
        }
        this.authorities = customGrantedAuthorities;
        this.mobile="7044654415";
        this.userId=user.getId();
    }
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
