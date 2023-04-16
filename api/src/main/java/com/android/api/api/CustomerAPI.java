package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerAPI {
    @Autowired
    CustomerService customerService;

    @GetMapping("/{email}")
    public ResponseEntity<?> findById(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(customerService.findByEmail(email));
    }
}
