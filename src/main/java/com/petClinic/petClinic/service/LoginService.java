package com.petClinic.petClinic.service;

import com.petClinic.petClinic.commen.models.ProjectDetails;
import com.petClinic.petClinic.entity.User;
import com.petClinic.petClinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean tryLogin(String userName, String password, int projectId, String sessionId){

        Optional<User> optionalUser = userRepository.findUserByUserName(userName, projectId);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            System.out.println(user);

            if (user.getUserName().equals(userName) &&
                    user.getPassword().equals(password) &&
                    user.getProjectId() == projectId){

                ProjectDetails projectDetails = ProjectDetails.getInstance();
                projectDetails.addUser(sessionId, user);
                return true;
            }
        }

        return false;
    }

    public User getUserByUserName(String userName){
        return  null;
    }
}
