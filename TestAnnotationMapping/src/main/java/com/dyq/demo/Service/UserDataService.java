package com.dyq.demo.Service;

import com.dyq.demo.entity.Role;
import com.dyq.demo.entity.UserData;

import java.util.List;

public interface UserDataService {
    UserData find(Long id);
    UserData save(UserData userData);
    void delete(Long id);
    void update(UserData userData);
    List<UserData> findAll();
}
