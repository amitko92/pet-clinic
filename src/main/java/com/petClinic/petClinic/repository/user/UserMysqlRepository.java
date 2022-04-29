package com.petClinic.petClinic.repository.user;

import com.petClinic.petClinic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserMysqlRepository implements UserRepository{

    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("userName"),
                rs.getString("password"),
                rs.getInt("projectId"));

        return user;
    };

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User findUserByID(Long id) {
        return null;
    }

    @Override
    public Optional<User> findUserByUserName(String userName, int projectId) {

        String sqlQuery = "select * from users where username=? and projectId=?";
        User user = null;

        try{

            user = jdbcTemplate.queryForObject(sqlQuery,new Object[]{userName, projectId}, rowMapper);
        }catch (DataAccessException e){

            System.out.println("user not found with userName= " +
                    userName + " and projectId " + projectId);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public User findUserByProjectId(int projectId) {
        return null;
    }

    @Override
    public List<User> findAllUsersByProjectId(int projectId) {

        String sqlQuery = "select * from users where projectId=1";
        return jdbcTemplate.query(sqlQuery, rowMapper);
    }

    @Override
    public boolean isUserExistsByID(Long id) {
        return false;
    }

    @Override
    public boolean isUserExistsByProjectId(int projectId) {
        return false;
    }
}
