package com.example.userservice.repository;

import com.example.userservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Session getByToken(String token);

    Session save(Session session);
}
