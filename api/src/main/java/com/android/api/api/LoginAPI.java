package com.android.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;
import net.minidev.json.JSONObject;

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

    /*
     * Sử dụng LoginDto để tạo nhận thông tin người dùng nhập vào
     * Gói tin sẽ qua JWT filter mà nhóm đã xây dựng sẵn để đăng nhập và token sẽ
     * được trả về với khoảng thời gian 1 tuần
     *
     */

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        String jwt = accountService.login(loginDto.getUsername(), loginDto.getPassword());

        if (jwt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid username or password!!");
        }

        return ResponseEntity.ok().body(jwt);
    }

    /*
     * Sử dụng SignUpDto để nhận thông tin người dùng nhập vào
     * Sau đó tạo tài khoản cho khách hàng và gửi mail xác thực
     * cho tài khoản này và trả về đối tượng JSON
     */

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) throws Exception {
        Account account = signUpDto.toAccountEntity();
        String message = accountService.registerUser(account, signUpDto.getEmail());

        Customer customer = signUpDto.toCustomerEntity(account);

        session.setAttribute("customer", customer);
        session.setAttribute("account", account);

        JSONObject jo = new JSONObject();
        jo.put("account", account);
        jo.put("customer", customer);
        jo.put("message", message);

        return ResponseEntity.status(HttpStatus.CREATED).body(jo);
    }

    /*
     * Sau khi gửi email xác thực thì người dùng sẽ sử dụng đoạn mã xác thực (thời
     * gian hiệu lực là 5 phút) để xác thực tài khoản
     */

    @PostMapping(value = { "/signup/verify-email" })
    public ResponseEntity<?> verifyEmail(@RequestParam("code") String code, @RequestParam("account") String newAccount,
            @RequestParam("customer") String newCustomer)
            throws Exception {

        Account temp = new Gson().fromJson(newAccount, Account.class);

        String username = temp.getUsername();

        boolean result = accountService.verifyRegister(username, code);
        if (result == false) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Verify email fail!!");
        }
        Account account = accountService.findByUsername(username).get();

        Customer customer = new Gson().fromJson(newCustomer, Customer.class);
        // customer.setAccount(account);
        // customerService.save(customer);

        customerService.createCustomerAfterRegister(customer, account);

        return ResponseEntity.status(200).body("Verify email successfully!!");
    }

    /*
     * Chức năng quên mật khẩu
     * Người dùng sẽ nhập email và sau đó sẽ tìm tài khoản có email trùng email đã
     * nhập và sau đó sẽ gửi email xác thực cho email đó
     */

    @PostMapping(value = { "/forget-password" })
    public ResponseEntity<?> forgetPassword(@RequestParam("email") String email) throws Exception {
        Customer customer = customerService.findByEmail(email);

        Account account = customer.getAccount();

        accountService.generateOneTimePassword(account, email);
        accountService.save(account);
        // session.setAttribute("account", account);
        JSONObject jo = new JSONObject();
        jo.put("message", "Please check your email!!!");
        jo.put("account", account);

        return ResponseEntity.ok().body(jo);
    }

    /*
     * Chức năng gửi lại OTP cho tài khoản
     */
    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOTP(@RequestParam("email") String email, @RequestParam("account") String jsonAccount)
            throws Exception {
        Account tempAccount = new Gson().fromJson(jsonAccount, Account.class);

        Account account = accountService.findByUsername(tempAccount.getUsername()).get();

        accountService.generateOneTimePassword(account, email);
        accountService.save(account);

        return ResponseEntity.ok().body("Please check your email!!!");
    }

    /*
     * Chức năng đặt lại mật khẩu
     * Sau khi quên mật khẩu và xác thực email xong thì sẽ tới bước đặt lại mật khẩu
     */
    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("reset-password") String jsonPasswordDto,
            @RequestParam("account") String jsonAccount) {
        Account account = new Gson().fromJson(jsonAccount, Account.class);

        ResetPasswordDto passwordDto = new Gson().fromJson(jsonPasswordDto, ResetPasswordDto.class);

        boolean result = accountService.resetPassword(account, passwordDto.getPassword(),
                passwordDto.getRepeatPassword(), passwordDto.getCode());
        return result == true ? ResponseEntity.ok().body("Reset password successfully!!!")
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Fail! Something get wrong!?");
    }
}
