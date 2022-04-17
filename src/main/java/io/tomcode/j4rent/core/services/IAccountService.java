package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Image;
import io.tomcode.j4rent.core.entities.OTP;
import io.tomcode.j4rent.exception.*;
import io.tomcode.j4rent.mapper.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Component
public interface IAccountService {

    Iterable<Account> getAllAccount();

    OTP register(Register account) throws PhoneNumberExistsException, UsernameExistsException, EmailIsExistsException;

    void verify(int otp) throws InvalidOTPException;

    UserInfo createAccount(CreateAccount account) throws PhoneNumberExistsException, UsernameExistsException, EmailIsExistsException, InvalidOTPException, IdCardIsExistsException;

    Boolean checkAccountExists(Register register) throws UsernameExistsException, PhoneNumberExistsException, EmailIsExistsException;

    void checkUserInfo(CreateAccount account) throws IdCardIsExistsException;

    Authentication getAuthentication();

    Account getAccountById(UUID id);

    Account getAccountByUsername(String username);

    JwtResponse authenticate(Login login);

    Account getCurrentAccount();

    UserInfo getCurrentUserInfo();

    UserInfo updateUser(UserInfo info) throws IdIsNotFoundException;

    Image updateAvatar(MultipartFile file) throws IdIsNotFoundException, ImageFailException;

    boolean checkUserPermission (UUID uuid , String namePermission );
}