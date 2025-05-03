package com.example.userservice.security.services;

import com.example.userservice.model.User;

import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<User> optionalUserDetails = userRepository.findByEmail(username);
       if(optionalUserDetails.isEmpty()) {
           throw new UsernameNotFoundException("User not found");
       }
       User user = optionalUserDetails.get();


        return new CustomUserDetails(user);
    }
}
