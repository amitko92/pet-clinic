package com.petClinic.petClinic.entity;

import javax.persistence.Entity;

@Entity
public class User {

    private Long ID = -1L;
    private String name = "";
    private String userName = "";
    private String password = "";
    private int projectId = -1;

    public User(Long ID, String name, String userName, String password, int projectId) {
        this.ID = ID;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.projectId = projectId;
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
