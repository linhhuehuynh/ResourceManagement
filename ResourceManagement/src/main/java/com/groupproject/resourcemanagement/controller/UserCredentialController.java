package com.groupproject.resourcemanagement.controller;

import com.groupproject.resourcemanagement.entity.UserCredentialEntity;
import com.groupproject.resourcemanagement.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/userCredential")
public class UserCredentialController {

    @Autowired
    private UserCredentialService userCredentialService;

    @PostMapping("")
    public void addUserCredential(@RequestBody UserCredentialEntity userCredential) {
        userCredentialService.addUserCredential(userCredential);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserCredentialEntity getUserCredential(@PathVariable int id) {
        return userCredentialService.getUserCredential(id);
    }

    @PutMapping("")
    public void setUserCredential(@RequestBody UserCredentialEntity userCredential) {
        userCredentialService.setUserCredential(userCredential);
    }

    @DeleteMapping("/{id}")
    public void deleteUserCredential(@PathVariable int id) {
        userCredentialService.deleteUserCredential(id);
    }
}
