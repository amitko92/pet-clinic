package com.petClinic.petClinic.commen.models;

import com.petClinic.petClinic.entity.Project;

/**
 * hold all project data in runtime
 */
public class ProjectData {

    private Project project;

    public ProjectData(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
