package com.petClinic.petClinic.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    public void process(Model model, HttpSession session) {

        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null) {
            messages = new ArrayList<>();
        }

        model.addAttribute("sessionMessages", messages);
    }
}
