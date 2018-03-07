package com.dyq.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentController {
    @GetMapping("/document")
    public String document() {
        return "document";
    }

}
