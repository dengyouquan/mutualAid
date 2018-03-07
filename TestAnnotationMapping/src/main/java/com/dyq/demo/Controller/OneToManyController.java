package com.dyq.demo.Controller;

import com.dyq.demo.Service.AddressService;
import com.dyq.demo.Service.RoleService;
import com.dyq.demo.Service.UserDataService;
import com.dyq.demo.Service.UserService;
import com.dyq.demo.entity.Address;
import com.dyq.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/om")
public class OneToManyController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AddressService addressService;
    @GetMapping("/add")
    public String add(){
        User user = userService.findByUsername("username");
        Address address = new Address("address","city","province",user);
        Address address1 = new Address("address","city","province",user);
        user.getAddresses().add(address);
        user.getAddresses().add(address1);
        userService.save(user);
        return "add";
    }

    @GetMapping("/addtwo")
    public String addtwo(){
        User user = new User("name","email","username","password",null,null,null);
        //user = userService.save(user); java.lang.NullPointerException: null
        userService.save(user);//address属性没有执行
        Address address = new Address("address","city","province",user);
        Address address1 = new Address("address","city","province",user);
        //先构造address
        List<Address> addresses = new LinkedList<>();
        addresses.add(address);
        addresses.add(address1);
        user.setAddresses(addresses);
        userService.save(user);
        return "addtwo";
    }
    @GetMapping("/remove")
    public String remove(){
        User user = userService.findByUsername("username");
        if(user!=null)
            userService.delete(user.getId());
        return "remove";
    }

    @GetMapping("/removed")
    public String removed(){
        User user = userService.findByUsername("username");
        //user.getAddresses().remove(0);//不会删除
        /*List<Address> addresses = user.getAddresses();
        addresses.remove(0);//移除是删不了的
        user.setAddresses(addresses);*/
        //userService.save(user);
        Address address = user.getAddresses().get(0);//删不了，映射的问题
        addressService.remove(address.getId());//ok 自定义SQL
        return "removed";
    }
    @GetMapping("/update")
    public String update(){
        User user = userService.findByUsername("username");
        user.setName("updatename");
        System.out.println("-----");
        Address address = user.getAddresses().get(0);
        address.setCity("updatecity");
        userService.save(user);
        return "update";
    }
    @GetMapping("/find")
    public String find(){
        User user = userService.findByUsername("username");//默认懒加载地址
        System.out.println("---");
        System.out.println(user.getAddresses().toString());
        return "find";
    }

    @GetMapping("/findd")
    public String findd(){
        Address address = addressService.find(1l);//懒加载
        return "findd";
    }
}
