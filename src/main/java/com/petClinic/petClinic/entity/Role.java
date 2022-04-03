package com.petClinic.petClinic.entity;

public class Role {

    private int ID = -1;
    private String name = "";

    public Role(int id, String name) {

        this.ID = id;
        this.name = name;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
