package com.android.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.android.api.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
    Optional<Account> findByUsername(String username);
}
