package com.android.api.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.android.api.entity.Account;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;

    public Account toEntity() {
        Account account = new Account();
        account.setUsername(this.getUsername());
        account.setHashCode(new BCryptPasswordEncoder().encode(this.getPassword()));
        
        return account;
    }
}
