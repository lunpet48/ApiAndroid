package com.android.api.security;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.android.api.entity.Account;
import com.android.api.repository.AccountRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        Account account = accountRepository.findByUsername(username).get();
        try {
            if (account == null) throw new AccountNotFoundException(username);
        } catch (AccountNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new AccountDetails(account);
    }
}
