package com.android.api.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.android.api.entity.Account;
import com.android.api.entity.Customer;

import lombok.Data;

@Data
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public Account toAccountEntity() {
        Account account = new Account();
        account.setUsername(this.getUsername());
        account.setHashCode(new BCryptPasswordEncoder().encode(this.getPassword()));
        
        return account;
    }

    public Customer toCustomerEntity(Account account) {
        Customer customer = new Customer();
        customer.setAccount(account);
        customer.setEmail(this.email);
        customer.setFirstName(this.firstName);
        customer.setLastName(this.lastName);
        return customer;
    }
}
