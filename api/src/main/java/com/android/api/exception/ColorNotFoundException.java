package com.android.api.exception;

public class ColorNotFoundException extends RuntimeException {
    public ColorNotFoundException(Long id){
        super("Could not found color with id: " + id);
    }
}
