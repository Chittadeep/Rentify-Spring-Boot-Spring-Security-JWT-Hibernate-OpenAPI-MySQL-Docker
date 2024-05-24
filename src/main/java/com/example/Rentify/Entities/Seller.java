package com.example.Rentify.Entities;

import java.util.List;

import com.example.Rentify.Model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Seller{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email
    @Column(unique=true)
    private String email;
    @Size(min=10, max = 10, message = "phone number should be of 10 digits")
    @Column(unique = true)
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy="seller")
    private List<Property> properties;

    public Seller(UserModel user)
    {
        this.email = user.getEmail();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.phoneNumber=user.getPhoneNumber();
    }
}
