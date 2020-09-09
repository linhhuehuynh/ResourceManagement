package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.User;
import com.example.demoresourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public User addUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @PutMapping("/{id}")
    public User setUserById(@RequestBody User user) {
        userService.setUserById(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return "Deleted user " + id;
    }


}
