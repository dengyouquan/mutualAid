package com.dyq.demo.controller.admin;

import com.dyq.demo.domain.User;
import com.dyq.demo.service.UserService;
import com.dyq.demo.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("admins")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @GetMapping("user")
    public ModelAndView template(Model model){
        return new ModelAndView("admins/user", "userModel", model);
    }
    @GetMapping("table/user")
    public ResponseEntity<Response> tableUser(@RequestParam(value="page",required=false,defaultValue="1") int pageIndex,
                                              @RequestParam(value="limit",required=false,defaultValue="10") int pageSize){
        Pageable pageable = new PageRequest(pageIndex-1,pageSize);//页面从1开始，查询从0开始
        Page<User> users = userService.findAll(pageable);
        return ResponseEntity.ok().body(new Response(0,"",userService.getUsers().size(),users.getContent()));
    }
}
