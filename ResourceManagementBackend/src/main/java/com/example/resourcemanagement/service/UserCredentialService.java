package com.example.resourcemanagement.service;

import com.example.resourcemanagement.dao.UserCredentialDao;
import com.example.resourcemanagement.entity.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserCredentialService implements UserDetailsService {

    @Autowired
    private UserCredentialDao userCredentialDao;

    public Optional<UserCredential> addUserCredential(UserCredential userCredential) {
        UserCredential existUserCredential = userCredentialDao.findByUsername(userCredential.getUsername());
        if(existUserCredential != null) {
            return Optional.empty();
        } else {
            userCredentialDao.save(userCredential);
            UserCredential createdUserCredential = userCredentialDao.findByUsername(userCredential.getUsername());
            createdUserCredential.setPassword("");
            return Optional.of(createdUserCredential);
        }
    }

    public Optional<UserCredential> getUserCredential(int id) {
        return userCredentialDao.findById(id);
    }

    public Optional<UserCredential> getUserCredentialByName(String username) {
        return Optional.of(userCredentialDao.findByUsername(username));
    }

    public Optional<UserCredential> setUserCredential(int id, UserCredential userCredential) {
        Optional<UserCredential> existUserCredential1 = userCredentialDao.findById(id);
        if(existUserCredential1.isPresent()) {
            UserCredential checkUserCredential = userCredentialDao.findByUsername(userCredential.getUsername());
            if(checkUserCredential != null) {
                if(checkUserCredential.getId() != id) {
                    return Optional.empty();
                }
            }
            userCredential.setId(id);
            UserCredential savedUser = userCredentialDao.save(userCredential);
            return Optional.of(savedUser);
        }
        return null;
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
