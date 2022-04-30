package com.petClinic.petClinic.commen.models;

import com.petClinic.petClinic.entity.User;

/** hold the project and user information.
 * class - projectData.
 * class - user.
 */
public class ProjectDetails {

    private ProjectData projectData;
    private User user;

    /**
     * @param projectData - the project's information.
     * @param user - the user's information.
     */
    public ProjectDetails(ProjectData projectData, User user) {
        this.projectData = projectData;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ProjectDetails{" +
                "projectData=" + projectData +
                ", user=" + user +
                '}';
    }

    public ProjectData getProjectData() {
        return projectData;
    }

    public void setProjectData(ProjectData projectData) {
        this.projectData = projectData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
