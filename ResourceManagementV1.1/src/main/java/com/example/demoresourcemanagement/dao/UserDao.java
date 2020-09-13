package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.Project;
import com.example.demoresourcemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
//
//    @Query("select user.projects from User user where user.userId = :id")
//    Optional<List<Project>> findAllProjectByUserId(@Param("id") int id);
}
