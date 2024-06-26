package com.example.Rentify.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Property cannot be created without place")
    private String place;
    @NotNull(message = "Property cannot be created without area")
    private String area;
    @NotNull(message = "Property cannot be created without bedrooms")
    private short numberOfBedrooms;
    @NotNull(message = "Property cannot be created without bathrooms")
    private short bathroom;
    @NotNull(message = "Property cannot be created without hospitals")
    private short hospitals;
    @NotNull(message = "Property cannot be created without colleges")
    private short colleges; 
    
    @ManyToMany(mappedBy = "interestedProperties", cascade = CascadeType.ALL)
    private List<Buyer> interestedBuyers;

    @JsonIgnore
    @NotNull(message = "Property cannot be created without seller")
    @ManyToOne
    private Seller seller;

    @Override
    public boolean equals(Object ob)
    {
        if(ob==this) return true;
        Property p = (Property) ob;
        if(p.id==this.id) return true;
        return false;
    }
}
