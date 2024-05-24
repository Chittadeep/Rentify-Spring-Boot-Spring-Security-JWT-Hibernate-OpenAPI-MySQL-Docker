package com.example.Rentify.Exceptions;

public class BuyerNotFoundException extends RuntimeException{

    @Override
    public String getMessage() {
        return "No Buyer with the given ID exists";
    }
    
}
