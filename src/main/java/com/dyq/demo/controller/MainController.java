package com.dyq.demo.controller;

import com.dyq.demo.domain.Document;
import com.dyq.demo.util.FileUtil;
import com.dyq.demo.vo.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    /*@GetMapping("/index")
    public String index() {
        return "index";
    }*/

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/emoji")
    public String emoji() {
        return "emoji";
    }
    @GetMapping("/showcomment")
    public String showcomment() {
        return "comment";
    }

    @GetMapping("/faillogin")
    public String loginError(Model model,HttpServletRequest request) {
        System.out.println("faillogin");
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登陆失败，账号或者密码错误！");
        HttpSession httpSession = request.getSession();
        Object e = httpSession.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        if(e instanceof LockedException){
            httpSession.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,new LockedException("用户输入密码错误3次，已被锁定，请联系管理员解锁"));
        }
        else if(e instanceof InternalAuthenticationServiceException){
            httpSession.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,new InternalAuthenticationServiceException("该用户不存在"));
        }
        else if(e instanceof BadCredentialsException){
            httpSession.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,new BadCredentialsException("用户名或密码错误"));
        }
        System.out.println(e);
        return "login";
    }
}
