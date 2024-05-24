package com.example.Rentify.Entities;

import com.example.Rentify.Model.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class LoginDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Email
    @Column(unique = true)
    private String email;
    
    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public LoginDetails(UserModel user, UserType userType)
    {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userType = userType;
    }

}
