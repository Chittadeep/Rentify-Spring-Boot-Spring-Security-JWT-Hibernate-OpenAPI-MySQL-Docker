package com.example.Rentify.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Rentify.Entities.LoginDetails;
import com.example.Rentify.Repositories.LoginDetailsRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private LoginDetailsRepository loginDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginDetails loginDetails = loginDetailsRepository.getLoginDetailsByEmail(username);
        return org.springframework.security.core.userdetails.User.builder().username(loginDetails.getEmail())
        .password(passwordEncoder.encode(loginDetails.getPassword())).authorities(loginDetails.getUserType().name()).build();
    }
    
}
