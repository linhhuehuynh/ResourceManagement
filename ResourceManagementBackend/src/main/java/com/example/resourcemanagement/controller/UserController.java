package com.example.resourcemanagement.controller;

import com.example.resourcemanagement.entity.User;
import com.example.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try{
            Optional<User> user = userService.getUserById(id);
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> getUserAll() {
        Optional<List<User>> existUserList = userService.getUserAll();
        if(existUserList.isPresent()) {
            return new ResponseEntity<>(existUserList.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No User List Found!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> setUserById(@PathVariable int id, @RequestBody User user) {
        Optional<User> existUser = userService.setUserById(id, user);
        if (existUser.isPresent()) {
            return new ResponseEntity<>(existUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        Optional<User> existUser = userService.deleteUserById(id);
        if(existUser.isPresent()) {
            return new ResponseEntity<>("Deleted User Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found!", HttpStatus.NOT_FOUND);
    }
}
