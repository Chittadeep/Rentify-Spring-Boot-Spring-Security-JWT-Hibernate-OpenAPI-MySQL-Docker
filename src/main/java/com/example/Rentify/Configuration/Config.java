package com.example.Rentify.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {
    
 @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
        public AuthenticationManager getAuthenticationManager(UserDetailsService userDetailsService,
                        PasswordEncoder passwordEncoder, HttpSecurity httpSecurity) throws Exception {
                AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
                builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
                return builder.build();
        }

     @Bean
        public SecurityFilterChain configure(HttpSecurity httpSecurity)throws Exception {
        httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers("/register/buyer").permitAll()
        .requestMatchers("/register/seller").permitAll()
        .requestMatchers("/register/property").hasAnyAuthority("SELLER")
        .requestMatchers("/allProperty").hasAnyAuthority("BUYER", "SELLER")
        .requestMatchers("/interested/property/**").hasAnyAuthority("BUYER")
        .requestMatchers("/property/**").hasAnyAuthority("SELLLER", "BUYER")
        .anyRequest().authenticated().
        and().httpBasic();

        return httpSecurity.build();
        }
}
