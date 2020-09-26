package com.example.resourcemanagement.dao;

import com.example.resourcemanagement.entity.ProjectColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectColumnDao extends JpaRepository<ProjectColumn, Integer> {

    @Query("select p from ProjectColumn p where p.project.id = ?1")
    Optional<List<ProjectColumn>> getAllByProjectId(int projectId);

//    @Modifying
//    @Query("delete from ProjectColumn p where p.project.id = ?1")
//    void deleteAllByProjectId(int projectId);
}