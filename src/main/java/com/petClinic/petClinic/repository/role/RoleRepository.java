package com.petClinic.petClinic.repository.role;

import com.petClinic.petClinic.entity.Role;
import com.petClinic.petClinic.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository {

    public Role findRoleByID(Long id);

    public Optional<Role> findRoleByName(String roleName);

    public Optional<List<Role>> findAllRolesByUserId(int userId);

    public boolean isRoleExistsByRoleID(Long id);
}
