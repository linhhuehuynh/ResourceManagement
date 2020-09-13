package com.groupproject.resourcemanagement.service;

import com.groupproject.resourcemanagement.dao.UserCredentialDao;
import com.groupproject.resourcemanagement.entity.UserCredentialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialService {

    @Autowired
    private UserCredentialDao userCredentialDao;

    public void addUserCredential(UserCredentialEntity userCredential) {
        userCredentialDao.save(userCredential);
    }

    public UserCredentialEntity getUserCredential(int id) {
        return userCredentialDao.getOne(id);
    }

    public void setUserCredential(UserCredentialEntity userCredential) {
        userCredentialDao.equals(userCredential);
    }

    public void deleteUserCredential(int id) {
        userCredentialDao.deleteById(id);
    }
}
