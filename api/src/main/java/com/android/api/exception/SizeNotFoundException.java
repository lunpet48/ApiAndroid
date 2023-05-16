package com.android.api.exception;

public class SizeNotFoundException extends RuntimeException {
    public SizeNotFoundException(Long id){
        super("Could not found size with id: " + id);
    }
}
