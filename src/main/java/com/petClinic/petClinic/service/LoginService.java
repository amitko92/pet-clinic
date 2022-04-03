package com.petClinic.petClinic.service;

import com.petClinic.petClinic.commen.models.ProjectDetails;
import com.petClinic.petClinic.entity.Role;
import com.petClinic.petClinic.entity.User;
import com.petClinic.petClinic.repository.RoleRepository;
import com.petClinic.petClinic.repository.UserRepository;
import com.petClinic.petClinic.repository.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public LoginService(UserRepository userRepository,
                        RoleRepository roleRepository,
                        ProjectRepository projectRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * @param userName - the user's userName.
     * @param password - the user's password.
     * @param projectId - the user's projectId
     * @param sessionId - the user's sessionId
     * @return boolean - true is has user in data base with userName, password and projectId.
     */
    public boolean tryLogin(String userName, String password, int projectId, String sessionId){

        Optional<User> optionalUser = userRepository.findUserByUserName(userName, projectId);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            ArrayList<Role> roles = null;

            if (user.getUserName().equals(userName) &&
                    user.getPassword().equals(password) &&
                    user.getProjectSerialNum() == projectId){

                Optional<List<Role>> optionalRoles = roleRepository.findAllRolesByUserId(user.getID());
                roles = (ArrayList<Role>) optionalRoles.get();
                user.setRoles(roles);
                System.out.println(user);
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
