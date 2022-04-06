package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.OTP;
import io.tomcode.j4rent.exception.EmailExistsException;
import io.tomcode.j4rent.exception.InvalidOTPException;
import io.tomcode.j4rent.exception.PhoneNumberExistsException;
import io.tomcode.j4rent.exception.UsernameExistsException;
import io.tomcode.j4rent.mapper.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public interface IAccountService {

    Iterable<Account> getAllAccount();

    OTP register(Register account);

    void verify(int otp) throws InvalidOTPException;

    UserInfo createAccount(CreateAccount account) throws PhoneNumberExistsException, UsernameExistsException, EmailExistsException, InvalidOTPException;

    Boolean checkAccountExists(Register register) throws UsernameExistsException, PhoneNumberExistsException, EmailExistsException;

    Authentication getAuthentication();

    Account getAccountById(UUID id);

    Account getAccountByUsername(String username);

    JwtResponse authenticate(Login login);

    Account getCurrentAccount();

    UserInfo getCurrentUserInfo();
}