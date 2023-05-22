package com.android.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.android.api.entity.Account;

import jakarta.persistence.Tuple;

public interface AccountService {
    List<String> login(String username, String password);

    String registerUser(Account account, String email) throws Exception;

    Optional<Account> findByUsername(String username);

    List<Account> findAll();

    Optional<Account> findById(Long id);

    Account save(Account account);

    void sendEmailVerify(String username, String email, String OTP) throws Exception;

    boolean verifyRegister(String username, String code) throws Exception;

    boolean verifyResetPassword(Account account, String code) throws Exception;

    void generateOneTimePassword(Account account, String email) throws Exception;

    void clearOTP(Account account);

    boolean resetPassword(Account account, String password, String repeatPassword, String code);
}