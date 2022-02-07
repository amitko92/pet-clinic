package com.petClinic.petClinic.repository;

import com.petClinic.petClinic.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    public User findUserByID(Long id);

    public Optional<User> findUserByUserName(String userName, int projectId);

    public User findUserByProjectId(int projectId);

    public List<User> findAllUsersByProjectId(int projectId);

    public boolean isUserExistsByID(Long id);

    public boolean isUserExistsByProjectId(int projectId);
}
