package com.example.resourcemanagement.dao;

import com.example.resourcemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
//
//    @Query("select user.projects from User user where user.userId = :id")
//    Optional<List<Project>> findAllProjectByUserId(@Param("id") int id);
}
