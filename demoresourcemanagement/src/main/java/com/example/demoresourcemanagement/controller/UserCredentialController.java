package com.example.demoresourcemanagement.controller;

import com.example.demoresourcemanagement.entity.UserCredential;
import com.example.demoresourcemanagement.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userCredential")
public class UserCredentialController {

    @Autowired
    private UserCredentialService userCredentialService;

    @PostMapping("")
    public void addUserCredential(@RequestBody UserCredential userCredential) {
        userCredentialService.addUserCredential(userCredential);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserCredential getUserCredential(@PathVariable int id) {
        return userCredentialService.getUserCredential(id);
    }

    @PutMapping("")
    public void setUserCredential(@RequestBody UserCredential userCredential) {
        userCredentialService.setUserCredential(userCredential);
    }

    @DeleteMapping("/{id}")
    public void deleteUserCredential(@PathVariable int id) {
        userCredentialService.deleteUserCredential(id);
    }
}

