package com.android.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.android.api.exception.AccountNotFoundException;
import com.android.api.exception.ColorNotFoundException;
import com.android.api.exception.ProductNotFoundException;
import com.android.api.exception.SizeNotFoundException;

@ControllerAdvice
public class AccountNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String accountNotFoundHandler(AccountNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFoundHandler(ProductNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SizeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String sizeNotFoundHandler(SizeNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ColorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String colorNotFoundHandler(ColorNotFoundException ex) {
        return ex.getMessage();
    }

}
