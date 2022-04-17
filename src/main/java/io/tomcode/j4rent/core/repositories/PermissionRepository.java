package io.tomcode.j4rent.core.repositories;

import io.tomcode.j4rent.core.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends BaseRepository<Permission, UUID> {


}