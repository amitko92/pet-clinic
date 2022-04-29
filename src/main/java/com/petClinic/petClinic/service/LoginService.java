package com.petClinic.petClinic.service;

import com.petClinic.petClinic.core.helpers.UserAuthentication;
import com.petClinic.petClinic.core.session.LoginUsersManager;
import com.petClinic.petClinic.entity.Role;
import com.petClinic.petClinic.entity.User;
import com.petClinic.petClinic.repository.role.RoleRepository;
import com.petClinic.petClinic.repository.user.UserRepository;
import com.petClinic.petClinic.repository.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    private final UserService userService;
    private final RoleService roleService;
    private final ProjectRepository projectRepository;
    private final LoginUsersManager loginUsersManager;

    @Autowired
    public LoginService(
            UserService userService,
            RoleService roleService,
            ProjectRepository projectRepository,
            LoginUsersManager loginUsersManager){

        this.userService = userService;
        this.roleService = roleService;
        this.projectRepository = projectRepository;
        this.loginUsersManager = loginUsersManager;
    }

    /**
     * @param userName - the user's userName.
     * @param password - the user's password.
     * @param projectId - the user's projectId
     * @param sessionId - the user's sessionId
     * @return boolean - true is has user in data base with userName, password and projectId.
     */
    public boolean tryLogin(String userName, String password, int projectId, String sessionId){

        User user = null;
        ArrayList<Role> roles = null;
        UserAuthentication authenticateUser = new UserAuthentication();

        Optional<User> optionalUser = userService.getUser(projectId, userName);

        if(optionalUser.isEmpty()) {

            return false;
        }

        user = optionalUser.get();

        if (!authenticateUser.authenticateUser(user, userName, password, projectId)){

            return false;
        }

        Optional<List<Role>> optionalRoles = roleService.getAllRoles(user);
        roles = (ArrayList<Role>) optionalRoles.get();
        user.setRoles(roles);
        System.out.println(user);

        loginUsersManager.addUser(sessionId, user);

        return true;
    }

}
