package com.example.resourcemanagement.service;

import com.example.resourcemanagement.dao.UserDao;
import com.example.resourcemanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Optional<User> getUserById(int id) {
        return userDao.findById(id);
    }

    public Optional<List<User>> getUserAll() {
        try {
            List<User> userList = userDao.findAll();
            return Optional.of(userList);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }

    }

    public Optional<User> setUserById(int id, User user) {
        Optional<User> existUser = userDao.findById(id);
        if(existUser.isPresent()) {
            user.setId(id);
            User savedUser = userDao.save(user);
            return Optional.of(savedUser);
        }
        return Optional.empty();
    }

    public Optional<User> deleteUserById(int id) {
        Optional<User> existUser = userDao.findById(id);
        if(existUser.isPresent()) {
            userDao.deleteById(id);
            return Optional.of(existUser.get());
        }
        return Optional.empty();
    }
}
