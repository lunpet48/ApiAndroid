package com.android.api.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import jakarta.mail.internet.MimeMessage;

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

    @Autowired
    private JavaMailSender mailSender;

    private Integer verifyerCode;

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
        // account.setHashCode(passwordEncoder.encode(account.getHashCode()));
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

    @Override
	public void sendEmailVerify(String username, String email) throws Exception {
		verifyerCode = (int) ((Math.random() * (999999 - 100000)) + 100000);
		String subject = "Please verify your account";
		String senderName = "Luan dep trai";
		String mailContent = "<p>Dear :" + username + ",</p>";
		mailContent += "<p>Plase enter the verifyer code below to verify your account.</p>";

		mailContent += "<h1>" + String.valueOf(verifyerCode) + "</h1>";

		mailContent += "<p>Thank You <br> Luan dep trai Team</p>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("20110675@student.hcmute.edu.vn", senderName);
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(mailContent, true);

		mailSender.send(message);

	}

    @Override
    public boolean verifyRegister(String username, int code) throws Exception {
        Account account = accountRepository.findByUsername(username).get();
        if (account != null && code == verifyerCode) {
            account.setIsActive((byte) 1);
            return true;
        }
        return false;
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
