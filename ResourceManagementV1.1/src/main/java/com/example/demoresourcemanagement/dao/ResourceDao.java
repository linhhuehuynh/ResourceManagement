package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.Project;
import com.example.demoresourcemanagement.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.Optional;


public interface ResourceDao extends JpaRepository<Resource, Integer>, JpaSpecificationExecutor<Resource> {

}
