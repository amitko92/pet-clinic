package com.petClinic.petClinic.entity;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class User {

    private int ID = -1;
    private String name = "";
    private String userName = "";
    private String password = "";
    private int projectSerialNum = -1;
    private ArrayList<Role> roles = new ArrayList<Role>();

    public User(int ID, String name, String userName, String password, int projectSerialNum) {
        this.ID = ID;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.projectSerialNum = projectSerialNum;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    public int getID() {
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

    public int getProjectSerialNum() {
        return projectSerialNum;
    }

    public void setID(int ID) {
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

    public void setProjectSerialNum(int projectSerialNum) {
        this.projectSerialNum = projectSerialNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", projectId=" + projectSerialNum +
                ", roles=" + roles +
                '}';
    }
}
