package com.example.Rentify.Exceptions;

public class PropertyNotFoundException extends RuntimeException{

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return "Property with this Id does not exist";
    }
    
}
