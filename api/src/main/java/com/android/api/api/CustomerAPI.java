package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.entity.Customer;
import com.android.api.exception.CustomerNotFoundException;
import com.android.api.security.JwtTokenProvider;
import com.android.api.service.CustomerService;
import com.android.api.utils.TokenUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class CustomerAPI {
    @Autowired
    CustomerService customerService;

    @Autowired
    JwtTokenProvider provider;    

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        String token = TokenUtils.getToken(request);
        Long customerId = provider.getCustomerIdFromJwt(token);
        
        return ResponseEntity.ok().body(customerService.findById(customerId).get());
    }

    @PutMapping("/profile/update")
    public ResponseEntity<?> updateProfile(@RequestBody Customer customerUpdate, HttpServletRequest request) {
        String token = TokenUtils.getToken(request);
        Long customerId = provider.getCustomerIdFromJwt(token);

        Customer customer = customerService.findById(customerId).orElseThrow(() -> new CustomerNotFoundException());
        Customer result = customerService.updateCustomerForCustomer(customer, customerUpdate);
        return ResponseEntity.ok().body(result);
    }
}
