package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.OTP;
import io.tomcode.j4rent.exception.*;
import io.tomcode.j4rent.mapper.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public interface IAccountService {

    Iterable<Account> getAllAccount();

    OTP register(Register account) throws PhoneNumberExistsException, UsernameExistsException, EmailExistsException;

    void verify(int otp) throws InvalidOTPException;

    UserInfo createAccount(CreateAccount account) throws PhoneNumberExistsException, UsernameExistsException, EmailExistsException, InvalidOTPException, IdCardExistsException;

    Boolean checkAccountExists(Register register) throws UsernameExistsException, PhoneNumberExistsException, EmailExistsException;

    void checkUserInfo(CreateAccount account) throws IdCardExistsException;

    Authentication getAuthentication();

    Account getAccountById(UUID id);

    Account getAccountByUsername(String username);

    JwtResponse authenticate(Login login);

    Account getCurrentAccount();

    UserInfo getCurrentUserInfo();

    UserInfo updateUser(UserInfo info) throws IdNotFoundException;
}