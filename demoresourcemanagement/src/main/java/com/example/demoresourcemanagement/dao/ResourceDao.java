package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceDao extends JpaRepository<Resource, Integer> {
}
