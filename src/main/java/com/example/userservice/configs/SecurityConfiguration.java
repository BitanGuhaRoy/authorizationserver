package com.example.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .anyRequest().permitAll()  // Allow all requests
//                )
//                .csrf().disable()             // Disable CSRF for simplicity (optional, but common when security is disabled)
//                .formLogin().disable()       // Disable form login
//                .httpBasic().disable();      // Disable basic auth
//
//        return http.build();
//    }
}
