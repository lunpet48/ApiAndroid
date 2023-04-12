package com.android.api.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(Long id){
        super("Could not find account " + id);
    }

    public AccountNotFoundException(String username) {
        super("Could not find account " + username);
    }

}