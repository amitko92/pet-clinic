package com.petClinic.petClinic.commen.models;

import com.petClinic.petClinic.entity.Project;
import com.petClinic.petClinic.entity.User;

public class ProjectData {

    private Project project;
    private User user;

    public ProjectData(Project project, User user) {
        this.project = project;
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
