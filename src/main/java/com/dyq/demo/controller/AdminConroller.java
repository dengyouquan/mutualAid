package com.dyq.demo.controller;

import com.dyq.demo.domain.Authority;
import com.dyq.demo.domain.User;
import com.dyq.demo.service.AuthorityService;
import com.dyq.demo.service.UserService;
import com.dyq.demo.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class AdminConroller {
    private static final Long ROLE_USER_AUTHORITY_ID = 2L;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/setauthority")
    public ResponseEntity<Response> setauthority() {
        Authority authority = new Authority("ROLE_ADMIN");
        authority.setId(1L);
        Authority authority1 = new Authority("ROLE_USER");
        authority1.setId(2L);
        Authority authority2 = new Authority("ROLE_TEACHER");
        authority2.setId(3L);
        authorityService.saveAuthority(authority);
        authorityService.saveAuthority(authority1);
        authorityService.saveAuthority(authority2);
        return ResponseEntity.ok().body(new Response(0, "建立角色成功",0,""));
    }

    /**
     * 注册用户
     * @param user
     * @param result
     * @param redirect
     * @return
     */
    @PostMapping("/register")
    public String registerUser(User user) {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
        user.setAuthorities(authorities);
        user.setAvatar("/images/avatar/large/elliot.jpg");
        System.out.println(user.toString());
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping
    public ModelAndView list(@RequestParam(value="pageIndex",required=false,defaultValue="1") int pageIndex,
                             @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                             @RequestParam(value="name",required=false,defaultValue="") String name,
                             Model model) {

        Pageable pageable = new PageRequest(pageIndex-1, pageSize);
        Page<User> page = userService.getUsersByNameLike(name, pageable);
        List<User> list = page.getContent();	// 当前所在页面数据列表
        System.out.println(list.toString());
        model.addAttribute("page", page);
        model.addAttribute("userList", list);
        return new ModelAndView("users/list", "userModel", model);
    }

    /**
     * 新建用户
     * @param user
     * @param result
     * @param redirect
     * @return
     */
    @PostMapping
    public ModelAndView create(User user, Long authorityId) {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        user.setAvatar("/images/avatar/large/elliot.jpg");
        if(user.getId() == null) {
            user.setEncodePassword(user.getPassword()); // 加密密码
        }else {
            User originalUser = userService.getUserById(user.getId());
            //重新赋值create_at
            user.setCreatedAt(originalUser.getCreatedAt());
            // 判断密码是否做了变更
            String rawPassword = originalUser.getPassword();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePasswd = encoder.encode(user.getPassword());
            boolean isMatch = encoder.matches(rawPassword, encodePasswd);
            if (!isMatch) {
                user.setEncodePassword(user.getPassword());
            }else {
                user.setPassword(user.getPassword());
            }
        }
        System.out.println("save:"+user.toString()+",userid:"+user.getId());
        try {
            userService.saveUser(user);
        }  catch (ConstraintViolationException e)  {
            return new ModelAndView("redirect:/users");
             }
        return new ModelAndView("redirect:/users");
    }

    /**
     * 获取 form 表单页面
     * @param user
     * @return
     */
    @GetMapping("/add")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User(null, null, null, null));
        return new ModelAndView("users/add", "userModel", model);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model) {
        try {
            userService.removeUser(id);
        } catch (Exception e) {
            return  ResponseEntity.ok().body( new Response(0, e.getMessage(),0,""));
        }
        return  ResponseEntity.ok().body( new Response(0, "处理成功",0,""));
    }

    /**
     * 获取修改用户的界面，及数据
     * @param user
     * @return
     */
    @GetMapping(value = "edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return new ModelAndView("users/edit", "userModel", model);
    }
}
