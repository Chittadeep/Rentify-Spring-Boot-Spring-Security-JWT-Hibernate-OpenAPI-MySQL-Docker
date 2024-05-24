package com.example.Rentify.Exceptions;

public class BuyerWasAlreadyInterestedException extends RuntimeException {

    @Override
    public String toString() {
        return "Buyer was already interested in this property";
    }
    
}
