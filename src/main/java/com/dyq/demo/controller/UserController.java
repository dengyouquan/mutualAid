package com.dyq.demo.controller;


import com.dyq.demo.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/userspace")
    public String userspace(Model model) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        model.addAttribute("user",principal);
        return "userspace";
    }


    @GetMapping("/avatar")
    public String avatar() {
        return "avatar";
    }
}
