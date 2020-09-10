package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectDao extends JpaRepository<Project, Integer>, JpaSpecificationExecutor<Project> {

}

