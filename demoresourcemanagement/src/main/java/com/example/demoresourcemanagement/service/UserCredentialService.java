package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.UserCredentialDao;
import com.example.demoresourcemanagement.entity.UserCredential;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserCredentialService {

    @Autowired
    private UserCredentialDao userCredentialDao;

    public void addUserCredential(UserCredential userCredential) {
        userCredentialDao.save(userCredential);
    }

    public ResponseEntity<UserCredential> getUserCredential(int id) {
        try {
            Optional<UserCredential> existUserCredential = userCredentialDao.findById(id);
            return new ResponseEntity<>(existUserCredential.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void setUserCredential(UserCredential userCredential) {
        userCredentialDao.equals(userCredential);
    }

    public void deleteUserCredential(int id) {
        userCredentialDao.deleteById(id);
    }
}
