package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

}
