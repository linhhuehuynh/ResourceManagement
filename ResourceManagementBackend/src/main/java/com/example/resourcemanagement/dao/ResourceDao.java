package com.example.resourcemanagement.dao;

import com.example.resourcemanagement.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ResourceDao extends JpaRepository<Resource, Integer>, JpaSpecificationExecutor<Resource> {

}
