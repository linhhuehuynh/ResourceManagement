package com.groupproject.resourcemanagement.service;

import com.groupproject.resourcemanagement.dao.UserDao;
import com.groupproject.resourcemanagement.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(UserEntity user) {
        userDao.save(user);
    }

    public UserEntity getUserById(int id) {
        return userDao.getOne(id);
    }

    public void setUserById(UserEntity user) {
        userDao.save(user);
    }

    public void deleteUserById(int id) {
        userDao.deleteById(id);
    }
}
