package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.android.api.service.AccountService;

@RestController
@RequestMapping("/auth")
public class LoginAPI {

    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        String jwt = accountService.login(loginDto.getUsername(), loginDto.getPassword()); 


        return ResponseEntity.ok().body("Login successfully with jwt: " + jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) throws Exception{
        Account account = signUpDto.toEntity();
        String message = accountService.registerUser(account);
        accountService.sendEmailVerify(account.getUsername(), signUpDto.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/signup/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam("email") String email, @RequestParam("code") int code) {
        
    } 

}
