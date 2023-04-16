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

    void sendEmailVerify(String username, String email, String OTP) throws Exception;

    boolean verifyRegister(String username, String code) throws Exception;

    void generateOneTimePassword(Account account, String email) throws Exception;

    void clearOTP(Account account);
}