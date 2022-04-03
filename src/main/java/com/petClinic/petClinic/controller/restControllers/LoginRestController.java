package com.petClinic.petClinic.controller.restControllers;

import com.petClinic.petClinic.commen.models.ProjectDetails;
import com.petClinic.petClinic.entity.Project;
import com.petClinic.petClinic.entity.User;
import com.petClinic.petClinic.service.LoginService;
import com.petClinic.petClinic.service.ProjectService;
import com.petClinic.petClinic.service.SessionManager;
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
    private final ProjectService projectService;

    @Autowired
    public LoginRestController(LoginService loginService, ProjectService projectService){

        this.loginService = loginService;
        this.projectService = projectService;
    }

    @PostMapping(path = "login")
    Map<String,Object> goToLogin(Model model,
                     HttpSession session,
                     @RequestParam("userName") String userName,
                     @RequestParam("password") String password,
                     @RequestParam("projectId") int projectId)
    {
        System.out.println("session.getId() " + session.getId());
        Map<String,Object> jsonObj = new HashMap<>();
        jsonObj.put("status", -1);
        jsonObj.put("message", "-1");
        boolean loginSuccess = false;

        if(userName.equals("") || password.equals("")){

            jsonObj.put("message", "one input or more is empty");
            return jsonObj;
        }

        loginSuccess = loginService.tryLogin(userName, password, projectId, session.getId());

        if(!loginSuccess){

            session.invalidate();
            jsonObj.put("message", "failed to login to server");
            return jsonObj;
        }

        SessionManager sessionManager = new SessionManager();
        sessionManager.process(model, session);
        User user = null;
        ProjectDetails projectDetails = ProjectDetails.getInstance();

        user = projectDetails.getUserBySessionId(session.getId());

        if(user == null){

            session.invalidate();
            jsonObj.put("message", "success to login, but failed to retrieve the user");
        }

        Optional<Project> project = projectService.getProjectBySerialNumber(user.getProjectSerialNum());

        if(project.isEmpty()){

            session.invalidate();
            jsonObj.put("message", "success to login, but failed to retrieve the project");
        }

        System.out.println("project " + project.get());
        jsonObj.put("message", "success");
        jsonObj.put("status", 1);
        jsonObj.put("project", project.get());
        jsonObj.put("user", user);
        return jsonObj;
    }

}
