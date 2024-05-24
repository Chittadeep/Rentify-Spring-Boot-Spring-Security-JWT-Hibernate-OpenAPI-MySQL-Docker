package com.example.Rentify.Exceptions;

public class SellerNotFoundException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Seller with the given id does not exists";
    }
    
}
