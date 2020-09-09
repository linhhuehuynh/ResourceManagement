package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ResourceDao extends JpaRepository<Resource, Integer> {

    Resource findByCreateDate(Date createDate);

    Optional<Resource> findByParentResourceId(Integer id);


}
