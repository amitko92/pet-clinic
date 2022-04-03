package com.petClinic.petClinic.repository;

import com.petClinic.petClinic.entity.Role;
import com.petClinic.petClinic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleMysqlRepository implements RoleRepository{

    RowMapper<Role> rowMapper = (rs, rowNum) -> {
        Role role = new Role(
                rs.getInt("id"),
                rs.getString("name"));
        return role;
    };

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Role findRoleByID(Long id) {
        return null;
    }

    @Override
    public Optional<Role> findRoleByName(String roleName) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Role>> findAllRolesByUserId(int userId) {

        String sqlQuery = "select * " +
                "from users_to_roles " +
                "inner join roles " +
                "on users_to_roles.role_id=roles.id " +
                "where users_to_roles.user_id=?";
        List<Role> roles = null;

        try{

            roles = jdbcTemplate.query(sqlQuery, new Object[]{userId}, new int[]{1}, rowMapper);
        }catch (DataAccessException e){

            System.out.printf("not founds roles for user with id= %d%n", userId);
        }

        return Optional.ofNullable(roles);
    }

    @Override
    public boolean isRoleExistsByRoleID(Long id) {
        return false;
    }
}
