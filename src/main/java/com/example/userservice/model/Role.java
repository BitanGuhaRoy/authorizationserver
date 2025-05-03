package com.example.userservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class Role extends BaseModel {

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<User> users;
    private String name;
}
