package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Account;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends BaseRepository<Account, UUID> {

    Account findByIdCardEquals(String idCard);

    Account findAccountByUsername(String userName);

    Account findAccountByEmail(String email);

    Account findAccountByPhoneNumber(String numberPhone);

    Account findByUsernameEquals(@NonNull String username);

    Account findByEmailEquals(String email);

    Account findByPhoneNumberEquals(String phoneNumber);
}
