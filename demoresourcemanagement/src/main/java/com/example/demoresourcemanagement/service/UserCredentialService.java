package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.UserCredentialDao;
import com.example.demoresourcemanagement.entity.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialService {

    @Autowired
    private UserCredentialDao userCredentialDao;

    public void addUserCredential(UserCredential userCredential) {
        userCredentialDao.save(userCredential);
    }

    public UserCredential getUserCredential(int id) {
        return userCredentialDao.getOne(id);
    }

    public void setUserCredential(UserCredential userCredential) {
        userCredentialDao.equals(userCredential);
    }

    public void deleteUserCredential(int id) {
        userCredentialDao.deleteById(id);
    }
}
