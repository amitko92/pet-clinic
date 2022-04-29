package com.petClinic.petClinic.core.session;

import com.petClinic.petClinic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginUsersManager {

    private HashMap<String, User> loginUsers;

    @Autowired
    public LoginUsersManager(){

        loginUsers = new HashMap<>();
    }

    public void addUser(String sessionId, User user){

        loginUsers.put(sessionId, user);
    }

    public void removeUser(String sessionId){

        loginUsers.remove(sessionId);
    }

    public Optional<User> getUserBySessionId(String sessionId) {
        User user = null;

        if(loginUsers.containsKey(sessionId)){

            user = loginUsers.get(sessionId);
        }

        return Optional.ofNullable(user);
    }

    public List<User> getAllUsersByProjectId(int projectId){

        List<User> list = new ArrayList<>();

        for (Map.Entry<String, User> entry : loginUsers.entrySet()) {

            if(entry.getValue().getProjectSerialNum() == projectId)
                list.add(entry.getValue());
        }

        return list;
    }

}
