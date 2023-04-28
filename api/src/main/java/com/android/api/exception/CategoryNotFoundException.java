package com.android.api.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long cateId){
        super("Could not find category id: " + cateId);
    }
}
