package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialDao extends JpaRepository<UserCredential, Integer> {
}