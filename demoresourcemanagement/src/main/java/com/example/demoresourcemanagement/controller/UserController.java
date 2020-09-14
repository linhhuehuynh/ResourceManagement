package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.User;
import com.example.demoresourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("")
//    public User addUser(@RequestBody User user) {
//        userService.addUser(user);
//        return user;
//    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> getUserAll() {
        return userService.getUserAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> setUserById(@PathVariable int id, @RequestBody User user) {
        return userService.setUserById(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        return userService.deleteUserById(id);
    }

//    @GetMapping("/{id}/project")
//    public ResponseEntity<?> getAllProjectByUserId(@PathVariable int id) {
//        return userService.getAllProjectByUserId(id);
//    }

}
