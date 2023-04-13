package com.android.api.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.android.api.entity.Account;
import com.android.api.entity.Role;
import com.android.api.repository.AccountRepository;
import com.android.api.security.AccountDetails;
import com.android.api.security.JwtTokenProvider;
import com.android.api.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Account> findAll() {
        // TODO Auto-generated method stub
        List<Account> result = new ArrayList<>();
        accountRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public String registerUser(Account account) {
        if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            return "Username is already taken!!!";
        }
        //account.setHashCode(passwordEncoder.encode(account.getHashCode()));
        account.setIsActive((byte) 0);
        account.setIsDeleted((byte) 0);
        account.setSalt("1");
        account.setRole(Role.ROLE_CUSTOMER);

        accountRepository.save(account);

        return "Created account successfully!!!";
    }

    @Override
    public String login(String username, String password) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password));

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((AccountDetails) authentication.getPrincipal());
        System.out.println(jwt);
        return jwt;
    }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws
    // UsernameNotFoundException {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'loadUserByUsername'");
    // }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws
    // UsernameNotFoundException {
    // Account account = accountRepository.findByUsername(username).get();
    // if(account == null) {
    // throw new UsernameNotFoundException(username);
    // }
    // return new AccountDetails(account);
    // }
}
