package com.petClinic.petClinic.controller;

import com.petClinic.petClinic.commen.Exception.PetClinicException;
import com.petClinic.petClinic.commen.models.ProjectDetails;
import com.petClinic.petClinic.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @GetMapping(path = "/admin")
    String goToLogin(Model model, HttpSession session){

        ProjectDetails projectDetails = ProjectDetails.getInstance();
        User user = null;
        List<User> allUsers = null;

        user = projectDetails.getUserBySessionId(session.getId());

        if(user == null){

            return "endOfSession";
        }

        allUsers = projectDetails.getAllUsersByProjectId(user.getProjectId());
        model.addAttribute("user", user);
        model.addAttribute("allUsers", allUsers);
        return "adminDetails";
    }
}
