package com.example.Rentify.Exceptions;

public class BuyerWasNotPreviouslyInterestedException extends RuntimeException{
    
    @Override
    public String getMessage() {
        return "The Buyer was not previously interested in this property";
    }
}
