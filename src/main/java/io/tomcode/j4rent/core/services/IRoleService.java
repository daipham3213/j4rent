package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Role;
import io.tomcode.j4rent.mapper.RoleDetails;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public interface IRoleService {
    Role createRole(String role);

    PageImpl<RoleDetails> getAllRole(Pageable pageable);

    Role getRoleByName(String name);


    Role getRoleByIdUser(UUID uuid);

    boolean checkRolePermission(UUID uuid, String name);

}
