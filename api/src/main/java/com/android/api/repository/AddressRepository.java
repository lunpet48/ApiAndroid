package com.android.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Address;
import com.android.api.entity.Customer;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long>{
    List<Address> findByCustomer(Customer customer);
}
