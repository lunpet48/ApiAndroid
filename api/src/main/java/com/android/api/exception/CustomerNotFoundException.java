package com.android.api.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String email) {
        super("Could not found customer with email: " + email);
    }

    public CustomerNotFoundException() {
        super("Could not get customer's information");
    } 
}
