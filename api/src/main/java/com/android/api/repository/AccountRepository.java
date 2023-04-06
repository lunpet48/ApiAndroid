package com.android.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.android.api.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{
    Optional<Account> findByUsername(String username);
}
