package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.UserCredentialDao;
import com.example.demoresourcemanagement.entity.UserCredential;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserCredentialService implements UserDetailsService {

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

    public ResponseEntity<?> setUserCredential(int id, UserCredential userCredential) {
        Optional<UserCredential> existUserCredential1 = userCredentialDao.findById(id);
        if(existUserCredential1.isPresent()) {
            userCredential.setId(id);
            userCredential = userCredentialDao.save(userCredential);
            return new ResponseEntity<>(userCredential, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public UserCredential loadUserByUsername(String name) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialDao.findByUsername(name);
        if (userCredential == null){
            throw new UsernameNotFoundException("Username doesn't exist!");
        }
        return userCredential;
    }
}
