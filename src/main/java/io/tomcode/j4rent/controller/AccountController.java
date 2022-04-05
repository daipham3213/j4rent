package io.tomcode.j4rent.controller;


import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.services.IAccountService;
import io.tomcode.j4rent.core.entities.OTP;
import io.tomcode.j4rent.mapper.*;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "${app.security.cors.origin}", allowedHeaders = "*")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public ResponseEntity<UserInfo> currentUser() {
        UserInfo account = accountService.getCurrentUserInfo();
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Account>> getAll() {
        Authentication auth = accountService.getAuthentication();

        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseResult> register(@RequestBody Register register) {
        try {
            accountService.checkAccountExists(register);
            accountService.register(register);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", "OTP sent"), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseResult> create(@RequestBody CreateAccount account) {
        try {
            UserInfo newAccount = accountService.createAccount(account);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", newAccount), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Object> verifyOTP(@RequestBody VerifyOTP otp) {
        try {
            accountService.verify(otp.getOtp());
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", "Valid OTP"), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseResult> login(@RequestBody Login login) {
        JwtResponse response = accountService.authenticate(login);
        return new ResponseEntity<>(new ResponseResult(HttpStatus.OK, "", response), HttpStatus.OK);
    }
}
