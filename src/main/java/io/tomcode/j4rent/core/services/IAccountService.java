package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Account;
import org.springframework.stereotype.Component;


@Component
public interface IAccountService {
    Iterable<Account> getAllAccount();
    Account createAccount(Account account);
}
