package com.dyq.demo.Service;

import com.dyq.demo.Repository.UserDataRepository;
import com.dyq.demo.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService {
    @Autowired
    private UserDataRepository userDataRepository;

    @Override
    public UserData find(Long id) {
        return userDataRepository.findOne(id);
    }

    @Override
    public UserData save(UserData userData) {
        if (userData.getUser()==null)
        {
            System.out.println("不能插入没有User的UserData！");
            return null;
        }
        //防止插入多个UserData
        if(userData.getUser().getUserData()!=null
                && userData.getUser().getUserData().getId()!=userData.getId()){
            System.out.println("不能插入多个UserData！");
            return userData;
        }
        return userDataRepository.save(userData);
    }

    @Override
    public void delete(Long id) {
        userDataRepository.delete(id);
    }

    @Override
    public void update(UserData userData) {
        userDataRepository.save(userData);
    }

    @Override
    public List<UserData> findAll() {
        return userDataRepository.findAll();
    }
}
