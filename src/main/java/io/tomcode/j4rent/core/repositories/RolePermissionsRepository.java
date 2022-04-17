package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.RolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RolePermissionsRepository extends BaseRepository<RolePermissions, UUID> {
    List<RolePermissions> findByRole_NameIs(String name);



}