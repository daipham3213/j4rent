package io.tomcode.j4rent.controller;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.services.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<String> testResponse() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Account>> getAll() {
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> register() {
        // admin
        // checkPermission(user, document_code, required_permission)
        // boolean isAuth = checkPermission(admin, "account", "create")

        return null;
    }

    ;
}
