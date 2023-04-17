package com.android.api.service;

import com.android.api.entity.Customer;

public interface CustomerService {
    Customer findByEmail(String Email);
    Customer save(Customer customer);
}
