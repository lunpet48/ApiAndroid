package com.android.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.android.api.exception.StorageNotFoundException;

@ControllerAdvice
public class StorageNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(StorageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String accountNotFoundHandler(StorageNotFoundException ex) {
        return ex.getMessage();
    }
}
