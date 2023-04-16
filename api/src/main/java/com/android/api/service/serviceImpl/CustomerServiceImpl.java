package com.android.api.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Customer;
import com.android.api.repository.CustomerRepository;
import com.android.api.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer findByEmail(String Email) {
        Optional<Customer> result = customerRepository.findByEmail(Email);
        return result.get();
    }
    
}
