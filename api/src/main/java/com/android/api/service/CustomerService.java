package com.android.api.service;

import java.util.Optional;

import com.android.api.entity.Account;
import com.android.api.entity.Customer;

public interface CustomerService {
    Customer findByEmail(String Email);
    Customer save(Customer customer);
    void createCustomerAfterRegister(Customer customer, Account account);
    Optional<Customer> findById(Long id);
    Customer updateCustomerForCustomer(Customer customer, Customer newCustomer);
    Long countCustomer();
}
