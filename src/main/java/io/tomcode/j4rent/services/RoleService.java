package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Role;
import io.tomcode.j4rent.core.repositories.RoleRepository;
import io.tomcode.j4rent.core.services.IRoleService;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public Iterable<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
      return roleRepository.findRoleByName(name);
    }




}
