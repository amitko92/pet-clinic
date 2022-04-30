package com.petClinic.petClinic.core.session;

import com.petClinic.petClinic.commen.models.ProjectData;
import com.petClinic.petClinic.commen.models.ProjectDetails;
import com.petClinic.petClinic.entity.Project;
import com.petClinic.petClinic.entity.User;
import com.petClinic.petClinic.service.ProjectService;
import com.petClinic.petClinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionManager {

    final ProjectService projectService;
    final LoginUsersManager loginUsersManager;

    @Autowired
    public SessionManager(ProjectService projectService, LoginUsersManager loginUsersManager) {
        this.projectService = projectService;
        this.loginUsersManager = loginUsersManager;
    }

    public boolean hesSession(String sessionId){

        Optional<ProjectDetails> optionalProjectDetails = getProjectDetails(sessionId);

        return optionalProjectDetails.isPresent();
    }

    public Optional<ProjectDetails> getProjectDetails(String sessionId){
        ProjectDetails projectDetails = null;

        User user = null;
        Optional<User> optionalUser = loginUsersManager.getUserBySessionId(sessionId);

        if(optionalUser.isEmpty()){
            return Optional.empty();
        }

        user = optionalUser.get();
        Optional<Project> optionalProject = projectService.getProject(user);

        if(optionalProject.isEmpty()){
            return Optional.empty();
        }

        ProjectData projectData = new ProjectData(optionalProject.get());
        projectDetails = new ProjectDetails(projectData, user);

        return Optional.ofNullable(projectDetails);
    }

}
