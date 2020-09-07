package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.User;
import com.example.demoresourcemanagement.service.UserService;
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
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

//    @GetMapping("")
//    @ResponseBody
//    public List<UserEntity> getUserAll() {
//        return userService.getUserAll(id);
//    }

    @PutMapping("")
    public void setUserById(@RequestBody User user) {
        userService.setUserById(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }


}
