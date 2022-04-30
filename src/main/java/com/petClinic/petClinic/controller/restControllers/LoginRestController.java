package com.petClinic.petClinic.controller.restControllers;

import com.petClinic.petClinic.commen.models.ProjectDetails;
import com.petClinic.petClinic.core.session.LoginUsersManager;
import com.petClinic.petClinic.core.session.SessionManager;
import com.petClinic.petClinic.entity.Project;
import com.petClinic.petClinic.entity.User;
import com.petClinic.petClinic.service.LoginService;
import com.petClinic.petClinic.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping(path="rest")
public class LoginRestController {

    private final LoginService loginService;
    private final SessionManager sessionManager;
    private final LoginUsersManager loginUsersManager;

    @Autowired
    public LoginRestController(
            LoginService loginService,
            SessionManager sessionManager,
            LoginUsersManager loginUsersManager){

        this.loginService = loginService;
        this.sessionManager = sessionManager;
        this.loginUsersManager = loginUsersManager;
    }

    @PostMapping(path = "login")
    Map<String,Object> goToLogin(
         Model model,
         HttpSession session,
         @RequestParam("userName") String userName,
         @RequestParam("password") String password,
         @RequestParam("projectId") int projectId){

        System.out.println("session.getId() " + session.getId());
        Map<String,Object> response = new HashMap<>();
        response.put("status", -1);
        response.put("message", "-1");

        if(userName.equals("") || password.equals("")){

            response.put("message", "one input or more is empty");
            return response;
        }

        Optional<ProjectDetails> optionalProjectDetails = loginService.tryLogin(userName, password, projectId, session.getId());

        if(optionalProjectDetails.isEmpty()){

            session.invalidate();
            response.put("message", "failed to login");
            return response;
        }

        System.out.println("project " + optionalProjectDetails.get());
        response.put("message", "success");
        response.put("status", 1);
        response.put("ProjectDetails", optionalProjectDetails.get());

        return response;
    }

}
