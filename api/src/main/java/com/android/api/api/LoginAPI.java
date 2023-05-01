package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.dto.LoginDto;
import com.android.api.dto.ResetPasswordDto;
import com.android.api.dto.SignUpDto;
import com.android.api.entity.Account;
import com.android.api.entity.Customer;
import com.android.api.security.JwtTokenProvider;
import com.android.api.service.AccountService;
import com.android.api.service.CustomerService;
import com.android.api.utils.TokenUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class LoginAPI {

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;

    @Autowired
    JwtTokenProvider provider;

    @Autowired
    HttpSession session;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        String jwt = accountService.login(loginDto.getUsername(), loginDto.getPassword());
        
        return ResponseEntity.ok().body(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) throws Exception {
        Account account = signUpDto.toAccountEntity();
        String message = accountService.registerUser(account, signUpDto.getEmail());

        Customer customer = signUpDto.toCustomerEntity(account);

        session.setAttribute("customer", customer);
        session.setAttribute("account", account);

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping(value = { "/signup/verify-email" })
    public ResponseEntity<?> verifyEmail(@RequestParam("code") String code) throws Exception {
        Account temp = (Account) session.getAttribute("account");
        session.removeAttribute("account");

        String username = temp.getUsername();

        boolean result = accountService.verifyRegister(username, code);
        if (result == false) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Verify email fail!!");
        }
        Account account = accountService.findByUsername(username).get();

        Customer customer = (Customer) session.getAttribute("customer");
        session.removeAttribute("customer");
        // customer.setAccount(account);
        // customerService.save(customer);

        customerService.createCustomerAfterRegister(customer, account);

        return ResponseEntity.status(200).body("Verify email successfully!!");
    }

    @PostMapping("/forget-password")
    public ResponseEntity<?> forgetPassword(@RequestParam("email") String email) throws Exception {
        Customer customer = customerService.findByEmail(email);

        Account account = customer.getAccount();

        accountService.generateOneTimePassword(account, email);
        accountService.save(account);
        session.setAttribute("account", account);

        return ResponseEntity.ok().body("Please check your email!!!");
    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto passwordDto) {
        Account account = (Account) session.getAttribute("account");
        // session.removeAttribute("account");
        boolean result = accountService.resetPassword(account, passwordDto.getPassword(),
                passwordDto.getRepeatPassword(), passwordDto.getCode());
        return result == true ? ResponseEntity.ok().body("Reset password successfully!!!")
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Fail! Something get wrong!?");
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(HttpServletRequest request) {
        String token = TokenUtils.getToken(request);

        Long customerId = provider.getCustomerIdFromJwt(token);

        return ResponseEntity.ok().body("Customer Id: " + customerId);
    }
}
