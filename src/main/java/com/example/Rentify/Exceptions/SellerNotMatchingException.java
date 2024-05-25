package com.example.Rentify.Exceptions;

public class SellerNotMatchingException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Property does not belong to the seller who wants to modify or delete";
    }
    
}
