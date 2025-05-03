package com.example.userservice.security.services;

import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtTokenService {

    private final JwtDecoder jwtDecoder;
    private final RoleRepository roleRepository;


    @Autowired
    public JwtTokenService(JwtDecoder jwtDecoder,RoleRepository roleRepository) {
        this.jwtDecoder = jwtDecoder;
        this.roleRepository = roleRepository;
    }

    public Optional<User> validateAndExtract(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            User user = new User();
            // Extract claims
            String username = jwt.getClaimAsString("user_name");
            user.setEmail(username);

            String mobile = jwt.getClaimAsString("mobile");

            String userId = jwt.getClaimAsString("user_id");
            user.setId(Long.parseLong(userId));

            List<String> roles = jwt.getClaimAsStringList("roles");
            List<Role> rolesUser = new ArrayList<>();
            for(String role:roles) {
                rolesUser.add(roleRepository.findByName(role));
            }
            user.setRoles(rolesUser);
            user.setUsername(jwt.getClaimAsString("user_name"));
            return Optional.of(user);

        } catch (JwtException ex) {
            // Token is invalid or expired

            return Optional.empty();
        }
    }
}
