package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.android.api.dto.LoginDto;
import com.android.api.dto.SignUpDto;
import com.android.api.entity.Account;
import com.android.api.entity.Customer;
import com.android.api.service.AccountService;
import com.android.api.service.CustomerService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class LoginAPI {

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;

    @Autowired
    HttpSession session;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        String jwt = accountService.login(loginDto.getUsername(), loginDto.getPassword()); 


        return ResponseEntity.ok().body("Login successfully with jwt: " + jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) throws Exception{
        Account account = signUpDto.toAccountEntity();
        String message = accountService.registerUser(account, signUpDto.getEmail());

        Customer customer = signUpDto.toCustomerEntity(account);
        
        session.setAttribute("customer", customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/signup/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam("username") String username, @RequestParam("code") String code) throws Exception {
        boolean result = accountService.verifyRegister(username, code);
        if (result == false) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Verify email fail!!");
        }
        Account account = accountService.findByUsername(username).get();
        
        Customer customer = (Customer) session.getAttribute("customer");
        // customer.setAccount(account);
        // customerService.save(customer);

        customerService.createCustomerAfterRegister(customer, account);

        return ResponseEntity.status(200).body("Verify email successfully!!");
    }

}
