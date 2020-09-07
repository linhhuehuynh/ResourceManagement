package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.UserDao;
import com.example.demoresourcemanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        userDao.save(user);
    }

    public User getUserById(int id) {
        return userDao.getOne(id);
    }

    public void setUserById(User user) {
        userDao.save(user);
    }

    public void deleteUserById(int id) {
        userDao.deleteById(id);
    }
}
