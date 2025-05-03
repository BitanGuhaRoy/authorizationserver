package com.example.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
public class Session extends BaseModel {
    private String token;
    private Date expiryDate;
    @OneToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;
}
