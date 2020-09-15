package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.UserDao;
import com.example.demoresourcemanagement.entity.Project;
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

    public ResponseEntity<?> getUserById(int id) {
        try{
            Optional<User> user = userDao.findById(id);
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
        }
    }

    public Optional<User> setUserById(int id, User user) {
        Optional<User> existUser = userDao.findById(id);
        if(existUser.isPresent()) {
            user.setId(id);

            User savedUser = userDao.save(user);
            Optional<User> result = Optional.of(savedUser);
            return result;
        }
        return Optional.empty();
    }

    public ResponseEntity<?> deleteUserById(int id) {
        Optional<User> existUser = userDao.findById(id);
        if(existUser.isPresent()) {
            userDao.deleteById(id);
            return new ResponseEntity<>("Deleted User Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getUserAll() {
        try {
            List<User> userList = userDao.findAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No User List Found!", HttpStatus.NOT_FOUND);
        }

    }

//    public ResponseEntity<?> getAllProjectByUserId(int id) {
//        Optional<List<Project>> existUser = userDao.findAllProjectByUserId(id);
//    }
}
