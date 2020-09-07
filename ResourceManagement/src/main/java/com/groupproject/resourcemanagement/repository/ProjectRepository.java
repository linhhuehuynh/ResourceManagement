package com.groupproject.resourcemanagement.repository;

import com.groupproject.resourcemanagement.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

    Optional<ProjectEntity> findByName(String name);

    //Optional<ProjectEntity> findByCreateDate(Date createDate);

   // Optional<ProjectEntity> findByUserId(Integer userId);

}
