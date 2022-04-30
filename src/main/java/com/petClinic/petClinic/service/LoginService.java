package com.petClinic.petClinic.service;

import com.petClinic.petClinic.commen.models.ProjectDetails;
import com.petClinic.petClinic.core.helpers.UserAuthentication;
import com.petClinic.petClinic.core.session.LoginUsersManager;
import com.petClinic.petClinic.core.session.SessionManager;
import com.petClinic.petClinic.entity.Role;
import com.petClinic.petClinic.entity.User;
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
    private final SessionManager sessionManager;
    private final LoginUsersManager loginUsersManager;

    @Autowired
    public LoginService(
            UserService userService,
            RoleService roleService,
            SessionManager sessionManager,
            LoginUsersManager loginUsersManager){

        this.userService = userService;
        this.roleService = roleService;
        this.sessionManager = sessionManager;
        this.loginUsersManager = loginUsersManager;
    }

    /** returning user with same userName, projectId and password from DB
     * @param userName - the user's userName.
     * @param password - the user's password.
     * @param projectId - the user's projectId
     * @param sessionId - the user's sessionId
     * @return Optional<User> - with User or null.
     */
    public Optional<ProjectDetails> tryLogin(String userName, String password, int projectId, String sessionId){

        User user = null;
        ArrayList<Role> roles = null;
        UserAuthentication authenticateUser = new UserAuthentication();

        Optional<User> optionalUser = userService.getUser(projectId, userName);

        if(optionalUser.isEmpty()) {

            System.out.println("[tryLogin] not found user");
            return Optional.ofNullable(null);
        }

        user = optionalUser.get();

        if (!authenticateUser.authenticateUser(user, userName, password, projectId)){

            // returning null even if
            // we found row in DB
            // because user entered wrong details.
            System.out.println("[tryLogin] fail to authenticate user");
            return Optional.ofNullable(null);
        }

        Optional<List<Role>> optionalRoles = roleService.getAllRoles(user);
        roles = (ArrayList<Role>) optionalRoles.get();
        user.setRoles(roles);

        loginUsersManager.addUser(sessionId, user);

        Optional<ProjectDetails> optionalProjectDetails = sessionManager.getProjectDetails(sessionId);

        System.out.println("[tryLogin] success to login");
        return optionalProjectDetails;
    }

}
