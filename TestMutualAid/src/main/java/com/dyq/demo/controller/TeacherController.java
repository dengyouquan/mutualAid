package com.dyq.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {

    @GetMapping("/teacher")
    public String teacher() {
        return "teacher";
    }

    @GetMapping("/userspace")
    public String userspace() {
        return "userspace";
    }

    @GetMapping("/rewardtask")
    public String rewardtask() {
        return "rewardtask";
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/avatar")
    public String avatar() {
        return "avatar";
    }


}
