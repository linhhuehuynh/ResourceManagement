package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.UserCredentialDao;
import com.example.demoresourcemanagement.entity.User;
import com.example.demoresourcemanagement.entity.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            userCredentialDao.save(userCredential);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialDao.findByUsername(username);
        if (userCredential == null){
            throw new UsernameNotFoundException("Username doesn't exist!");
        }
        return new org.springframework.security.core.userdetails.User(userCredential.getUsername(), userCredential.getPassword(),
                new ArrayList<>());
    }
}
