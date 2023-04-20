package com.android.api.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Account;
import com.android.api.entity.Customer;
import com.android.api.exception.CustomerNotFoundException;
import com.android.api.repository.CustomerRepository;
import com.android.api.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer findByEmail(String Email) {
        Customer result = customerRepository.findByEmail(Email).orElseThrow(() -> new CustomerNotFoundException(Email));
        return result;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void createCustomerAfterRegister(Customer customer, Account account) {
        customer.setAccount(account);
        customerRepository.save(customer);
    }
    
}
