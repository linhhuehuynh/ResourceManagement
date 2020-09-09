package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.Project;
import com.example.demoresourcemanagement.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ResourceDao extends JpaRepository<Resource, Integer> {

    Resource findByCreateDate(Date createDate);

}
