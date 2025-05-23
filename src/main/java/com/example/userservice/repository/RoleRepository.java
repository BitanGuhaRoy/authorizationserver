package com.example.userservice.repository;

import com.example.userservice.model.Role;
import com.example.userservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
