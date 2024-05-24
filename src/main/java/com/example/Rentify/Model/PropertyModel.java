package com.example.Rentify.Model;

import com.example.Rentify.Entities.Property;

import lombok.Data;

@Data
public class PropertyModel {
    private int id;
    private String place;
    private String area;
    private short numberOfBedrooms;
    private short bathroom;
    private short hospitals;
    private short colleges;
    private int interestedBuyers; 
    
    public PropertyModel(Property property)
    {
        this.id = property.getId();
        this.place=property.getPlace();
        this.area=property.getArea();
        this.numberOfBedrooms = property.getNumberOfBedrooms();
        this.bathroom = property.getBathroom();
        this.hospitals = property.getHospitals();
        this.colleges = property.getColleges();
        if(property.getInterestedBuyers()!=null)
            this.interestedBuyers = property.getInterestedBuyers().size();
        else this.interestedBuyers = 0;
        //System.out.println(property.getInterestedBuyers());
    }
}
