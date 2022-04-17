package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role, UUID> {

    List<Role> findByName(String name);

    Role findRoleByName(String name);

    Role findByAccounts_IdEquals(UUID id);

    boolean existsByRolePermissions_Permission_Name(String name);

    Role findByRolePermissions_Permission_NameIsAndAccounts_IdEquals(String name, UUID id);

    boolean existsByAccounts_IdEqualsAndRolePermissions_Permission_NameIs(UUID id, String name);

    boolean existsByAccounts_IdIsAndRolePermissions_Permission_NameIsAllIgnoreCase(UUID id, String name);


}