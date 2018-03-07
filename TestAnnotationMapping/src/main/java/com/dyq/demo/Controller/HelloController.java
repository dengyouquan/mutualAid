package com.dyq.demo.Controller;

import com.dyq.demo.Service.AddressService;
import com.dyq.demo.Service.RoleService;
import com.dyq.demo.Service.UserDataService;
import com.dyq.demo.Service.UserService;
import com.dyq.demo.entity.Address;
import com.dyq.demo.entity.User;
import com.dyq.demo.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/hello")
    public String hello() {
        User user = new User("name2", "email2", "username2", "password", null, null, null);
        UserData userData = new UserData(0, 0, 0, 0, null);
        /*user.setUserData(userData);
        userService.update(user);*/
        Address address1 = new Address("address1", "city1", "province1", null);
        Address address2 = new Address("address2", "city2", "province2", user);
        /*user.getAddresses().add(address1);
        user.getAddresses().add(address2);
        System.out.println(user.toString());
        userService.save(user);*/
        /*userService.save(user);
        address1.setUser(user);
        addressService.save(address1);*/


        /*addressService.save(address1);
        user.getAddresses().add(address1);
        userService.save(user);
        */
       /* userService.save(user);
        address1.setUser(user);
        addressService.save(address1);
        System.out.println(user.toString());*/
       /*User user1 = userService.findByUsername("username");
       user1.getAddresses().add(address1);
       userService.save(user1);
        System.out.println(user1.toString());
        //不更新user_id user不是维护方*/

        /*user.setUserData(userData);
        userService.update(user);
        //@OneToOne(mappedBy = "user")
        //private UserData userData;
        //bject references an unsaved transient instance - save the transient instance beforeQuery flushing :
        //com.dyq.demo.entity.User.userData -> com.dyq.demo.entity.UserData*/

        /*//user.setUserData(userData);
        //userService.update(user);
        //@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
        //private UserData userData;
        //ok*/

        /*//@OneToOne(mappedBy = "user")
        //private UserData userData;
        User user1 = userService.findByUsername("username");
        System.out.println(user1.toString());
        user1.getUserData().setIntegral(2);
        userService.update(user1);
        //不会删除userdata
        //会更新*/


        /*userData.setUser(user);
        userDataService.save(userData);
        // Error during managed flush [org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance beforeQuery flushing :
        // com.dyq.demo.entity.UserData.user -> com.dyq.demo.entity.User]
        //插入user_data发现user没有更新
        */

        /*userService.save(user);
        userData.setUser(user);
        userDataService.save(userData);
        ok*/

        /*User user1 = userService.findByUsername("username");
        System.out.println(user1.toString());
        UserData userData1 = user1.getUserData();
        userData1.getUser().setName("userdataname");
        userDataService.update(userData1);
        //能更新*/

        return "hello";
    }
    @GetMapping("/hi")
    public String hi() {

        //1.UserData作为维护方，双向关联

        /*//增（ok） UserData一定要和user关联，因为用的User保存
        //有可能重复插入
        User user = userService.findByUsername("1111");
        UserData userData = new UserData(1,1,1,1,user);
        user.setUserData(userData);
        userService.save(user);*/

        /*//删（no）删不掉  userData,user都为DETACH，能删掉
        //但是这样上面的增失败了，加上PERSIST，加上就删不掉
        //将UserData PERSIST,DETACH User REMOVE
        User user = userService.findByUsername("1111");
        //System.out.println(user.toString());
        System.out.println(user.getUserData().getId());
        userDataService.delete(user.getUserData().getId());*/

        /*//增 将UserData ALL User DETACH  能多次插入
        User user = userService.findByUsername("1111");
        UserData userData = new UserData(1,1,1,1,user);
        //user.setUserData(userData);
        userDataService.save(userData);*/

        /*//删 User 看是否级联 级联
        User user = userService.findByUsername("1111");
        userService.delete(user.getId());*/

        //修改 会级联修改
        User user = userService.findByUsername("1111");
        user.setName("33333");
        UserData userData = user.getUserData();
        System.out.println(userData.getId());
        userData.setBrowse(2);
        userDataService.save(userData);

        //user DETACH,


        //2.User作为维护方，双向外外键键关联
        /*//增（ok)
        //可以以重复插入，但是id变了
        User user = userService.findByUsername("1111");
        UserData userData = new UserData(1,1,1,1,user);
        user.setUserData(userData);
        userService.save(user);*/

        /*//删（ok）(把user的d_id置换为null,但实际上没有删除)
        User user = userService.findByUsername("1111");
        user.setUserData(null);
        userService.save(user);*/

        /*//删（ok）(删不掉)
        //UserData加上all，级联删除 不行
        User user = userService.findByUsername("1111");
        userDataService.delete(2l);*/

       /* //查询User,懒加载有效，
        User user = userService.findByUsername("1111");
        System.out.println(user.getUserData());*/

        /*//查询UserData,懒加载无效，
        UserData userData = userDataService.find(1l);*/

        /*//删除 user ALL改成DETACH  不会级联删除
        User user = userService.findByUsername("1111");
        userService.delete(1l);*/


        return "hi";
    }
    @GetMapping("/add")
    public String add(){
        User user = userService.findByUsername("username");
        UserData userData = new UserData(1,1,1,1,user);
        userDataService.save(userData);//单向关联
        return "add";
    }

    @GetMapping("/addtwo")
    public String addtwo(){
        User user = new User("name","email","username","password",null,null,null);
        UserData userData = new UserData(1,1,1,1,user);
        //user.setUserData(userData);//不用也可以双向关联
        userDataService.save(userData);
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
        if(user!=null)
            userDataService.delete(user.getUserData().getId());
        return "removed";
    }
    @GetMapping("/update")
    public String update(){
        //ok
        /*User user = userService.findByUsername("username");
        user.setName("updatename");
        System.out.println("-----");
        UserData userData = user.getUserData();
        System.out.println(userData.getId());
        userData.setBrowse(2);
        userDataService.save(userData);*/
        User user = userService.findByUsername("username");
        user.setName("updatename");
        System.out.println("-----");
        UserData userData = user.getUserData();
        System.out.println(userData.getId());
        userData.setBrowse(2);
        userService.save(user);
        return "update";
    }
    @GetMapping("/find")
    public String find(){
        User user = userService.findByUsername("username");//left outer join
        return "find";
    }

    @GetMapping("/findd")
    public String findd(){
        UserData userData = userDataService.find(1l);//eft outer join user user
        return "findd";
    }
}
