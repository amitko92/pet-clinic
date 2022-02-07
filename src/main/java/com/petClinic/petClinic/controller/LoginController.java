package com.petClinic.petClinic.controller;

import com.petClinic.petClinic.commen.Exception.PetClinicException;
import com.petClinic.petClinic.commen.models.ProjectDetails;
import com.petClinic.petClinic.entity.User;
import com.petClinic.petClinic.service.LoginService;
import com.petClinic.petClinic.service.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){

        this.loginService = loginService;
    }

    @GetMapping(path = "dashboard")
    String dashboard(Model model, HttpSession session){

        ProjectDetails projectDetails = ProjectDetails.getInstance();
        User user = null;

        user = projectDetails.getUserBySessionId(session.getId());

        if(user == null){

            return "endOfSession";
        }

        model.addAttribute("user", user);
        return "dashboard";
    }

    @GetMapping(path = "/login")
    String goToLogin(Model model){
        return "login";
    }

    @GetMapping(path = "/DoLogin")
    @RequestMapping(value="/DoLogin", method = RequestMethod.GET)
    String DoLogin(Model model,
                   HttpSession session,
                   @RequestParam("userName") String userName,
                   @RequestParam("password") String password,
                   @RequestParam("projectId") int projectId
    ){

        boolean loginSuccess = false;

        if(userName.equals("") || password.equals("")){

            return "login";
        }

        loginSuccess = loginService.tryLogin(userName, password, projectId, session.getId());

        if(!loginSuccess){

            session.invalidate();
            return "login";
        }

        SessionManager sessionManager = new SessionManager();
        sessionManager.process(model, session);
        User user = null;
        ProjectDetails projectDetails = ProjectDetails.getInstance();

        user = projectDetails.getUserBySessionId(session.getId());

        if(user == null){

            session.invalidate();
            return "login";
        }

        model.addAttribute("user", user);
        return "dashboard";
    }
}
