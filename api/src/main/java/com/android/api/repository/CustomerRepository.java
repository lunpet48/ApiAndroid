package com.android.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Account;
import com.android.api.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByAccount(Account account);
}
