package com.dyq.demo.Service;

import com.dyq.demo.entity.Role;
import com.dyq.demo.entity.User;

import java.util.List;

public interface RoleService {
    Role save(Role role);
    void delete(Role role);
    void update(Role role);
    List<Role> findAll();
}
