package com.petClinic.petClinic.controller.restControllers;

import com.petClinic.petClinic.core.session.LoginUsersManager;
import com.petClinic.petClinic.entity.User;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping(path="rest")
public class TestController {

    private final LoginUsersManager loginUsersManager;

    public TestController(LoginUsersManager loginUsersManager) {
        this.loginUsersManager = loginUsersManager;
    }

    @GetMapping(path = "test/users")
    Map<String,Object> getAllUsers(HttpSession session) {
        System.out.println("[getAllUsers] start");

        Map<String,Object> response = new HashMap<>();

        List<User> userList = loginUsersManager.getAllUsersByProjectId(1);

        response.put("users", userList.toString());
        response.put("status", "success");
        return response;
    }
}
