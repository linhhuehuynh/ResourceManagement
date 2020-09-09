package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectDao extends JpaRepository<Project, Integer> {
        Optional<Project> findByName(String name);
//
//    //Optional<ProjectEntity> findByCreateDate(Date createDate);
//
//   // Optional<ProjectEntity> findByUserId(Integer userId);
}
