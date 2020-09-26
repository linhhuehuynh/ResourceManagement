package com.example.resourcemanagement.dao;

import com.example.resourcemanagement.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialDao extends JpaRepository<UserCredential, Integer> {
    UserCredential findByUsername(String username);
}