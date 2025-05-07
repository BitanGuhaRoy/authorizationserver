package com.example.userservice.service;

import com.example.userservice.exceptions.InvalidCredentialException;
import com.example.userservice.exceptions.UserAlreadyExistsException;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.model.Role;
import com.example.userservice.model.Session;
import com.example.userservice.model.SessionStatus;
import com.example.userservice.model.User;
import com.example.userservice.repository.SessionRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.services.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private ObjectMapper objectMapper;


    private final SecretKey key = Jwts.SIG.HS256.key().build();


    @Override
    public User signUp(String email, String password) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(bCryptPasswordEncoder.encode(password));
            // send a welcome message
//            SendEmailDto sendEmailDto = new SendEmailDto();
//            sendEmailDto.setEmail("Thanks for signing up");
//            sendEmailDto.setSubject("Welcome to Platform->");
//            sendEmailDto.setTo(email);
//            sendEmailDto.setFrom("r.bitan");
//            kafkaProducerClient.sendMessage("send_email", objectMapper.writeValueAsString(sendEmailDto));
            userRepository.save(newUser);
            return newUser;
        }

    }

    @Override
    public String login(String email, String password) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User currentUser = user.get();
        if (!bCryptPasswordEncoder.matches(password, currentUser.getPassword()))
            throw new InvalidCredentialException("Invalid password");

        Session session = new Session();
        session.setUser(currentUser);
        session.setToken(createJWTToken(currentUser.getId(), currentUser.getRoles(), currentUser.getEmail()));
        session.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);
        return session.getToken();
    }

    @Override
    public Optional<User> ValidateToken(String token) {
//        try {
//
//            Jws<Claims> claimsJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
//            Claims body = claimsJws.getPayload();
//            Date expirationDate = body.getExpiration();
//            Long userId = body.get("userId", Long.class);
//            Session session = sessionRepository.getByToken(token);
//            if (session == null || session.getSessionStatus() == SessionStatus.INACTIVE) {
//                return Optional.empty();
//            }
//
//            return userRepository.findById(userId);
//
//        } catch (Exception e) {
//            return Optional.empty();
//        }

       return jwtTokenService.validateAndExtract(token);


//        return Optional.of(new User());


    }

    @Override
    public void logout(String token) {
        Session session = sessionRepository.getByToken(token);
        if (session != null) {
            session.setSessionStatus(SessionStatus.INACTIVE);
            sessionRepository.save(session);
        }
    }

    private String createJWTToken(Long userId, List<Role> roles, String email) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("roles", roles);
        claims.put("email", email);
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .issuer("bitan.roy")
                .signWith(key)
                .compact();
    }
}