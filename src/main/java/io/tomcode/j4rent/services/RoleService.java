package io.tomcode.j4rent.services;

import io.tomcode.j4rent.core.entities.Role;
import io.tomcode.j4rent.core.repositories.RoleRepository;
import io.tomcode.j4rent.core.services.IRoleService;
import io.tomcode.j4rent.mapper.RoleDetails;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("roleService")
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Role createRole(String role) {

        return roleRepository.save(new Role(role));
    }

    @Override
    public PageImpl<RoleDetails> getAllRole(Pageable pageable) {
        List<Role> roles = roleRepository.findAll();
        List<RoleDetails> roleDetails = new ArrayList<>();
        for (Role role : roles
        ) {
            roleDetails.add(modelMapper.map(role, RoleDetails.class));
        }
        return new PageImpl<RoleDetails>(roleDetails, pageable, pageable.getPageSize());

    }


    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }


}
