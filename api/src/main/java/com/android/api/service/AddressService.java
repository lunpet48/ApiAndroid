package com.android.api.service;

import java.util.List;

import com.android.api.entity.Address;
import com.android.api.entity.Customer;

public interface AddressService {
    Address save(Address address);
    List<Address> findByCustomer(Customer customer);
}
