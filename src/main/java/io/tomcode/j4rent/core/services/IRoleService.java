package io.tomcode.j4rent.core.services;

import io.tomcode.j4rent.core.entities.Role;
import org.springframework.stereotype.Component;

@Component
public interface IRoleService {
    Role createRole( Role role);

    Iterable<Role> getAllRole();

    Role getRoleByName(String name);

}
