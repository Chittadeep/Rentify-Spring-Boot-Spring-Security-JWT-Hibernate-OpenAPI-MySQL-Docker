package com.example.Rentify.Entities;

import java.util.List;

import com.example.Rentify.Model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Buyer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @Size(min=10, max = 10, message = "phone number should be of 10 digits")
    @Column(unique = true)
    private String phoneNumber;
    
    @JsonIgnore
    @ManyToMany
    private List<Property> interestedProperties;

    public Buyer(UserModel user)
    {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }

}
