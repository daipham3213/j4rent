package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Account;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends BaseRepository<Account, UUID> {
}
