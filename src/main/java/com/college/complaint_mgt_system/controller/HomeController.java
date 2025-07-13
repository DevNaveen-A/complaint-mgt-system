package com.college.complaint_mgt_system.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/dashboar")
    public String dashboard() {
        return "dashboard";
    }
}