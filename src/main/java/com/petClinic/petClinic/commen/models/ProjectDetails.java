package com.petClinic.petClinic.commen.models;

import com.petClinic.petClinic.commen.Exception.PetClinicException;
import com.petClinic.petClinic.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectDetails {

    private static ProjectDetails instance;
    private HashMap<String, User> loginUsers;

    private ProjectDetails(){

        loginUsers = new HashMap<>();
    }

    public void addUser(String sessionId, User user){

        loginUsers.put(sessionId, user);
    }

    public void removeUser(String sessionId){

        loginUsers.remove(sessionId);
    }

    public User getUserBySessionId(String sessionId) {

        if(!loginUsers.containsKey(sessionId)){

            return null;
        }

        return loginUsers.get(sessionId);
    }
    
    public List<User> getAllUsersByProjectId(int projectId){
        
        List<User> list = new ArrayList<>();

        for (Map.Entry<String, User> entry : loginUsers.entrySet()) {

            if(entry.getValue().getProjectId() == projectId)
                list.add(entry.getValue());
        }

        return list;
    }

    public static ProjectDetails getInstance(){

        if(instance == null){

            instance = new ProjectDetails();
        }

        return instance;
    }
}
