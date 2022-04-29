package com.petClinic.petClinic.service;

import com.petClinic.petClinic.core.session.LoginUsersManager;
import com.petClinic.petClinic.entity.User;
import com.petClinic.petClinic.repository.role.RoleRepository;
import com.petClinic.petClinic.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LoginUsersManager loginUsersManager;

    @Autowired
    public UserService(
            UserRepository userRepository,
            LoginUsersManager loginUsersManager){

        this.userRepository = userRepository;
        this.loginUsersManager = loginUsersManager;
    }

    /** find the user in data base by row id
     * @param userId - the user's id in data base.
     * @return User - return the user if not found return Optional.
     */
    public Optional<User> getUser(int userId){
        return  null;
    }

    /** find the user in data base by userName and projectId
     * @param userName - the user's userName.
     * @param projectId - the user's projectId
     * @return User - return the user if not found return Optional.
     */
    public Optional<User> getUser(int projectId, String userName){

        return userRepository.findUserByUserName(userName, projectId);
    }
}
