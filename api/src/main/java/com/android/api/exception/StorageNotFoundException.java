package com.android.api.exception;

public class StorageNotFoundException extends RuntimeException {
    public StorageNotFoundException(String filename) {
        super("Could not find the file: " + filename);
    }
    
}
