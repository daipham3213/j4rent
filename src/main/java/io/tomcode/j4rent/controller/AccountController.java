package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.configuration.SecurityConfig;
import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.services.IAccountService;
import io.tomcode.j4rent.core.entities.OTP;
import io.tomcode.j4rent.core.services.IJwtService;
import io.tomcode.j4rent.exception.EmailExistsException;
import io.tomcode.j4rent.exception.PhoneNumberExistsException;
import io.tomcode.j4rent.exception.UsernameExistsException;
import io.tomcode.j4rent.mapper.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController
@RequestMapping("/account")
public class AccountController {

    private final IAccountService accountService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;

    public AccountController(IAccountService accountService, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, IJwtService jwtService) {
        this.accountService = accountService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<String> testResponse() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
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
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK.value(), "", "OTP sent"), HttpStatus.OK);
        }catch ( UsernameExistsException e ) {
            return new ResponseEntity<>(new ResponseResult(HttpStatus.BAD_REQUEST.value(), "Username is exists", null), HttpStatus.BAD_REQUEST);
        }catch (EmailExistsException e){
            return new ResponseEntity<>(new ResponseResult(HttpStatus.BAD_REQUEST.value(), "Email is exists",null),HttpStatus.BAD_REQUEST);
        }catch (PhoneNumberExistsException e){
            return new ResponseEntity<>(new ResponseResult(HttpStatus.BAD_REQUEST.value(), "PhoneNumber is exists",null),HttpStatus.BAD_GATEWAY);
        }

        catch (Exception e ) {
            return new ResponseEntity<>(new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseResult> create(@RequestBody CreateAccount account) {
        try {
            Account newAccount = accountService.createAccount(account);
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK.value(), "", newAccount), HttpStatus.OK);
        } catch ( UsernameExistsException e ) {
            return new ResponseEntity<>(new ResponseResult(HttpStatus.BAD_REQUEST.value(), "Username is exists", null), HttpStatus.BAD_REQUEST);
        }catch (EmailExistsException e){
            return new ResponseEntity<>(new ResponseResult(HttpStatus.BAD_REQUEST.value(), "Email is exists",null),HttpStatus.BAD_REQUEST);
        }catch (PhoneNumberExistsException e){
            return new ResponseEntity<>(new ResponseResult(HttpStatus.BAD_REQUEST.value(), "PhoneNumber is exists",null),HttpStatus.BAD_GATEWAY);
        }
        catch (Exception e ) {
            return new ResponseEntity<>(new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Object> verifyOTP(@RequestBody OTP otp) {
        try {
            Account account = accountService.verify(otp.getOtp());
            if (account == null) {
                return new ResponseEntity<>(new ResponseResult(HttpStatus.NOT_FOUND.value(), "OTP NOT FOUND", null), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponseResult(HttpStatus.OK.value(), "", account), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JwtResponse> login(@RequestBody Login login) {
        JwtResponse response = accountService.authenticate(login);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
