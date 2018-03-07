package com.dyq.demo.Service;

import com.dyq.demo.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    void delete(Long id);
    void update(User user);
    List<User> findAll();
    User findByUsername(String username);
}
