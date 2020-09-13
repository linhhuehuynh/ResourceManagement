package com.groupproject.resourcemanagement.controller;

import com.groupproject.resourcemanagement.entity.UserEntity;
import com.groupproject.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public void addUser(@RequestBody UserEntity user) {
        userService.addUser(user);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserEntity getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

//    @GetMapping("")
//    @ResponseBody
//    public List<UserEntity> getUserAll() {
//        return userService.getUserAll(id);
//    }

    @PutMapping("")
    public void setUserById(@RequestBody UserEntity user) {
        userService.setUserById(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }


}
