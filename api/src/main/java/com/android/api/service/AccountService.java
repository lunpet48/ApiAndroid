package com.android.api.service;

import java.util.List;
import java.util.Optional;

import com.android.api.entity.Account;

public interface AccountService {
    String login(String username, String password);
    String registerUser(Account account);
    Optional<Account> findByUsername(String username);
    List<Account> findAll(); 
    Optional<Account> findById(Long id);
    Account save(Account account); 
}