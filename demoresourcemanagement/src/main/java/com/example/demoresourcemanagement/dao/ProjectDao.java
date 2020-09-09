package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.Project;

import com.example.demoresourcemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectDao extends JpaRepository<Project, Integer> {

}

