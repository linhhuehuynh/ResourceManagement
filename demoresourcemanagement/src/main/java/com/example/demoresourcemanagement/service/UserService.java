package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.UserDao;
import com.example.demoresourcemanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        userDao.save(user);
    }

    public ResponseEntity<User> getUserById(int id) {
        try{
            Optional<User> user = userDao.findById(id);
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<User> getAllUser() {return userDao.findAll();};

    public ResponseEntity<?> setUserById(int id, User user) {
        Optional<User> existUser = userDao.findById(id);
        if(existUser.isPresent()) {
            userDao.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteUserById(int id) {
        Optional<User> existUser = userDao.findById(id);
        if(existUser.isPresent()) {
            userDao.deleteById(id);
            return new ResponseEntity<>("Deleted User Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
    }
}
