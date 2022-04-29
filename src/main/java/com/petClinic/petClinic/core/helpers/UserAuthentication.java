package com.petClinic.petClinic.core.helpers;

import com.petClinic.petClinic.entity.User;

public class UserAuthentication {

    public static boolean authenticateUser(User user, String userName, String password, int projectId){

        if (user.getUserName().equals(userName) &&
            user.getPassword().equals(password) &&
            user.getProjectSerialNum() == projectId){

            return true;
        }

        return false;
    }
}
