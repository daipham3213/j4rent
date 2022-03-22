package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Account;
import io.tomcode.j4rent.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends BaseRepository<Role, UUID> {

    List<Role> findByName(String name);

    Role findRoleByName(String name);
}