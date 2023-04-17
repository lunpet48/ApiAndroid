package com.android.api.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.android.api.entity.Address;
import com.android.api.entity.Customer;
import com.android.api.repository.AddressRepository;
import com.android.api.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> findByCustomer(Customer customer) {
        return addressRepository.findByCustomer(customer);
    }
    
}
