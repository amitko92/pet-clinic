package com.petClinic.petClinic.service;

import com.petClinic.petClinic.entity.Role;
import com.petClinic.petClinic.entity.User;
import com.petClinic.petClinic.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<List<Role>> getAllRoles(User user){

        return roleRepository.findAllRolesByUserId(user.getID());
    }
}
