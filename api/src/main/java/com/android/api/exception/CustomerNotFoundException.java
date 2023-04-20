package com.android.api.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String email) {
        super("Could not found customer with email: " + email);
    }
}
