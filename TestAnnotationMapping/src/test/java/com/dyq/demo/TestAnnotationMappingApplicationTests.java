package com.dyq.demo;

import com.dyq.demo.Service.AddressService;
import com.dyq.demo.Service.RoleService;
import com.dyq.demo.Service.UserDataService;
import com.dyq.demo.Service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAnnotationMappingApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AddressService addressService;

	@Test
	public void contextLoads() {

	}

}
