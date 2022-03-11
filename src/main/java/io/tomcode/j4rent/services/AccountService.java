package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.repositories.AccountRepository;
import io.tomcode.j4rent.core.services.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service("accountService")
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Iterable<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
